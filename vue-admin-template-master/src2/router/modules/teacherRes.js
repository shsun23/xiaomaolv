/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const teacherResRouter = {
  path: '/teacherRes',
  component: Layout,
  redirect: '/teacherRes/menu1/menu1-1',
  name: 'teacherRes',
  meta: {
    title: '教学资源管理',
    icon: 'nested'
  },
  children: [
    {
      path: 'menu1',
      component: () => import('@/views/teacherRes/menu1/index'), // Parent router-view
      name: 'Menu1',
      meta: { title: '习题管理' },
      redirect: '/teacherRes/menu1/menu1-1',
      children: [
        // {
        //   path: 'menu1-1',
        //   component: () => import('@/views/teacherRes/menu1/menu1-1'),
        //   name: 'Menu1-1',
        //   meta: { title: 'Menu 1-1' }
        // },
        // {
        //   path: 'menu1-2',
        //   component: () => import('@/views/teacherRes/menu1/menu1-2'),
        //   name: 'Menu1-2',
        //   redirect: '/teacherRes/menu1/menu1-2/menu1-2-1',
        //   meta: { title: 'Menu 1-2' },
        //   children: [
        //     {
        //       path: 'menu1-2-1',
        //       component: () => import('@/views/teacherRes/menu1/menu1-2/menu1-2-1'),
        //       name: 'Menu1-2-1',
        //       meta: { title: 'Menu 1-2-1' }
        //     },
        //     {
        //       path: 'menu1-2-2',
        //       component: () => import('@/views/teacherRes/menu1/menu1-2/menu1-2-2'),
        //       name: 'Menu1-2-2',
        //       meta: { title: 'Menu 1-2-2' }
        //     }
        //   ]
        // },
        // {
        //   path: 'menu1-3',
        //   component: () => import('@/views/teacherRes/menu1/menu1-3'),
        //   name: 'Menu1-3',
        //   meta: { title: 'Menu 1-3' }
        // },
        // 添加
        {
          path: 'Briefanswer',
          component: () => import('@/views/teacherRes/menu1/Briefanswer'),
          name: 'Briefanswer',
          meta: { title: '简答题' }
        },
        {
          path: 'Fillblanks',
          component: () => import('@/views/teacherRes/menu1/Fillblanks'),
          name: 'Fillblanks',
          meta: { title: '填空题' }
        },
        {
          path: 'Judge',
          component: () => import('@/views/teacherRes/menu1/Judge'),
          name: 'Judge',
          meta: { title: '判断题' }
        },
        {
          path: 'Multipleselection',
          component: () => import('@/views/teacherRes/menu1/Multipleselection'),
          name: 'Multipleselection',
          meta: { title: '多选题' }
        },
        {
          path: 'Radio',
          component: () => import('@/views/teacherRes/menu1/Radio'),
          name: 'Radio',
          meta: { title: '单选题' }
        }
      ]
    },
    // {
    //   path: 'menu2',
    //   name: 'Menu2',
    //   component: () => import('@/views/teacherRes/menu2/index'),
    //   meta: { title: 'Menu 2' }
    // },
    {
      path: 'videoManagement',
      name: 'videoManagement',
      component: () => import('@/views/teacherRes/menu2/videoManagement'),
      meta: { title: '视频管理' }
    }
  ]
}

export default teacherResRouter
