import router from './router'
import store from './store'
import { Message } from 'element-ui'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import { getToken } from '@/utils/auth' // get token from cookie
import getPageTitle from '@/utils/get-page-title'

NProgress.configure({ showSpinner: false }) // NProgress Configuration

const whiteList = ['/login', '/register', '/404'] // no redirect whitelist

router.beforeEach(async(to, from, next) => {
  // start progress bar
  NProgress.start()

  // set page title
  document.title = getPageTitle(to.meta.title)

  // determine whether the user has logged in
  const hasToken = getToken()
  console.log("进入拦截")
  if (hasToken) {
    console.log("111")
    if (to.path === '/login') {
      // if is logged in, redirect to the home page
      // console.log("进入登录页面--")
      // console.log("From:" + from.path)
      // console.log(to.path)

      // console.log("用户身份："+store.getters.flag)
      // 在这里可以获取用户身份，按照指定的身份去指向地址
      next({ path: '/' })
      NProgress.done()
    } else {
      console.log("From:" + from.path)
      console.log(to.path)
      const hasGetUserInfo = store.getters.name
      if (hasGetUserInfo) {
        console.log("222")
        next()
      } else {
        console.log("333")
        try {
          // get user info
          await store.dispatch('user/getInfo')

          next()
        } catch (error) {
          // remove token and go to login page to re-login
          await store.dispatch('user/resetToken')
          Message.error(error || 'Has Error')
          next(`/login?redirect=${to.path}`)
          NProgress.done()
        }
      }
    }
  } else {
    /* has no token*/
    console.log("444")
    if (whiteList.indexOf(to.path) !== -1) {
      console.log("555")
      // in the free login whitelist, go directly
      next()
    } else {
      console.log("666")
      // other pages that do not have permission to access are redirected to the login page.
      console.log("to:path" + to.path)
      next(`/login?redirect=${to.path}`)
      NProgress.done()
    }
  }
})

router.afterEach(() => {
  // finish progress bar
  NProgress.done()
})
