package com.xiaomaolv.education.controller;

import com.xiaomaolv.education.pojo.question;
import com.xiaomaolv.education.utils.R;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("sturesult")
public class StudentResController {


    @GetMapping("question")
    public R getQuestion(){
        Date d = new Date();
        question q1 = new question(1, 2, "good", d, "aaa", 2, 2, "how are you", "A");
        question q2 = new question(2, 2, "good", d, "bba", 2, 2, "how about", "B");;
        List<question> qList = new ArrayList<>();
        qList.add(q1);
        qList.add(q2);

        return R.ok().data("items",qList).data("total", 2);

    }

    /*********** 注意这里的参数解析方法有错误***************
     * 当前端axios请求是get 方法，并且带有参数，后端使用HttpServletRequest request 接收参数
     * 并且可以通过String name = request.getParameter("name");获取到请求头中的值
     * 如：
     *  getQuestionList(){
     *     return request({
     *       url: '/sturesult/question',
     *       method: 'get',
     *       params:{
     *           name: "Bob",
     *           age: 15
     *       }
     *     })
     *   },
     *   注意：当前端使用了 params 表示参数是，使用get方法提交，会把参数放到请求头中，method：post不生效
     *   如果想使用post 方法提交，前端需要使用data：{"name":"zhangsan"}封装数据，后端使用下面方式获取
     *   public R BriefAnswer(@RequestBody Map params){
     *       sout(params.get("name"));
     *   }
     *   来源：https://www.hangge.com/blog/cache/detail_2485.html
     *************************************************/
//    @PostMapping("brief")
//    public R BriefAnswer(HttpServletRequest request){
//        System.out.println("进入BRIEF");
//        String ans = request.getParameter("ans");
//        System.out.println(ans);
////        System.out.println(name + ":" + age);
//        return R.ok().data("ans","1");
//    }
    @PostMapping("brief")
    public R BriefAnswer(@RequestBody Map params){
        System.out.println("进入BRIEF");
        System.out.println(params.get("ans"));
//        System.out.println(params.keySet());
//        System.out.println(name + ":" + age);
        return R.ok().data("ans","1");
    }
}
