import request from '@/utils/request'


export default {

  // 1. 查询题目
  getQuestionList(){
    return request({
      url: '/sturesult/question',
      method: 'get',

    })
  },


  // 2. 提交答案
  submitAns (ans){
    return request({
      url: '/sturesult/brief',
      // url: '/sturesult/brief',
      method: 'post',
      params: {
        "ans": ans
      },
      // headers: {
      //   'Content-Type': 'multipart/form-data'
      // }

    })
  },

}
