/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'
// import axios from 'axios'

const tableRouter = {
  path: '/table',
  component: Layout,
  redirect: '/table/complex-table',
  name: 'Table',
  meta: {
    // title: 'Table',
    title: '教学资源管理(实验)',
    icon: 'table'
  },
  children: [
    {
      path: 'dynamic-table',
      component: () => import('@/views/table/dynamic-table/index'),
      name: 'DynamicTable',
      meta: { title: 'Dynamic Table' }
    },
    {
      path: 'drag-table',
      component: () => import('@/views/table/drag-table'),
      name: 'DragTable',
      meta: { title: 'Drag Table' }
    },
    {
      path: 'inline-edit-table',
      component: () => import('@/views/table/inline-edit-table'),
      name: 'InlineEditTable',
      meta: { title: 'Inline Edit' }
    },
    {
      path: 'complex-table',
      component: () => import('@/views/table/complex-table'),
      name: 'ComplexTable',
      meta: { title: '教学视频' }
    },

    // 添加
    {
      path: 'test',
      component: () => import('@/views/table/test'),
      name: 'ComplexTable',
      meta: { title: '测试' }
    },
    {
      path: 'test1',
      component: () => import('@/views/table/dynamic-table/components/test1'),
      name: 'ComplexTable1',
      meta: { title: '测试1' }
    },
    {
      path: 'test2',
      component: () => import('@/views/table/dynamic-table/components/test2'),
      name: 'ComplexTable2',
      meta: { title: '测试2' }
    },
    {
      path: 'test3',
      component: () => import('@/views/table/dynamic-table/components/test3'),
      name: 'ComplexTable3',
      meta: { title: '测试3' }
    },
    {
      path: 'test4',
      component: () => import('@/views/table/dynamic-table/components/test4'),
      name: 'ComplexTable4',
      meta: { title: '测试4' }
    },
    {
      path: 'test5',
      component: () => import('@/views/table/dynamic-table/components/test5'),
      name: 'ComplexTable5',
      meta: { title: '测试5' }
    },
    {
      path: 'test6',
      component: () => import('@/views/table/dynamic-table/components/test6'),
      name: 'ComplexTable6',
      meta: { title: '测试6' }
    }
  ]
}
export default tableRouter
