/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const studentResRouter = {
  path: '/studentRes',
  component: Layout,
  redirect: '/studentRes/menu1/menu1-1',
  name: 'studentRes',
  meta: {
    title: '学生资源管理',
    icon: 'nested'
  },
  children: [
    {
      path: 'menu1',
      component: () => import('@/views/studentRes/menu1/index'), // Parent router-view
      name: 'Menu1',
      meta: { title: '习题库' },
      redirect: '/studentRes/menu1/menu1-1',
      children: [
        // 添加
        {
          path: 'Briefanswer',
          component: () => import('@/views/studentRes/menu1/Briefanswer'),
          name: 'Briefanswer',
          meta: { title: '简答题' }
        },
        {
          path: 'Fillblanks',
          component: () => import('@/views/studentRes/menu1/Fillblanks'),
          name: 'Fillblanks',
          meta: { title: '填空题' }
        },
        {
          path: 'Judge',
          component: () => import('@/views/studentRes/menu1/Judge'),
          name: 'Judge',
          meta: { title: '判断题' }
        },
        {
          path: 'Multipleselection',
          component: () => import('@/views/studentRes/menu1/Multipleselection'),
          name: 'Multipleselection',
          meta: { title: '多选题' }
        },
        {
          path: 'Radio',
          component: () => import('@/views/studentRes/menu1/Radio'),
          name: 'Radio',
          meta: { title: '单选题' }
        }
      ]
    },
    {
      path: 'videoManagement',
      name: 'videoManagement',
      component: () => import('@/views/studentRes/menu2/videoManagement'),
      meta: { title: '视频库' }
    }
  ]
}

export default studentResRouter
