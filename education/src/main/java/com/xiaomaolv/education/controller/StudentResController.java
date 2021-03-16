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

    @PostMapping("brief")
    public R BriefAnswer(HttpServletRequest request){
        System.out.println("进入BRIEF");
        String ans = request.getParameter("ans");
        System.out.println(ans);
//        System.out.println(name + ":" + age);
        return R.ok().data("ans","1");
    }
}
