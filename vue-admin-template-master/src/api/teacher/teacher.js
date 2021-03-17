import request from '@/utils/request'


export default {
  // 1. 老师列表查询
  getTeacherList (){
    return request({
      url: '/user/getlist',
      // url: '/sturesult/brief',
      method: 'get',
      // params: {
      //   name: "123",
      //   age: 15,
      // },

    })
  }
}
