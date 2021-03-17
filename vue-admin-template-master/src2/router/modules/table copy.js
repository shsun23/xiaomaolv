import Layout from '@/layout'

const tablecopyRouter = {
  path: '/table copy',
  component: Layout,
  redirect: '/table copy/complex-table',
  name: 'table copy',
  meta: {
    title: 'table copy',
    icon: 'table copy'
  },
  children: [
    {
      path: 'dynamic-table',
      component: () => import('@/views/table copy/dynamic-table/index'),
      name: 'Dynamictable copy',
      meta: { title: 'Dynamic table copy' }
    },
    {
      path: 'drag-table',
      component: () => import('@/views/table copy/drag-table'),
      name: 'Dragtable copy',
      meta: { title: 'Drag table copy' }
    },
    {
      path: 'inline-edit-table',
      component: () => import('@/views/table copy/inline-edit-table'),
      name: 'InlineEdittable copy',
      meta: { title: 'Inline Edit' }
    },
    {
      path: 'complex-table',
      component: () => import('@/views/table copy/complex-table'),
      name: 'Complextable copy',
      meta: { title: 'Complex table copy' }
    }
  ]
}
export default tablecopyRouter
