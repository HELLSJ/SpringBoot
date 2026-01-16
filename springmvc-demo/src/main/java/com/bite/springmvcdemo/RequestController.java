package com.bite.springmvcdemo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


@RequestMapping("/request")
@RestController
public class RequestController {
    @RequestMapping("/hello")
    public String say(){
        return "hello, Spring MVC";
    }

    @RequestMapping("/r1")
    public String r1(String name){
        return "接收到参数，name："+name;
    }
    @RequestMapping("/r2")
    public String r2(Student student){
        return "接收到参数, student: "+student;
    }

    @RequestMapping("/r6")
    public String r6(@RequestParam("name") String userName){
        return "接收到参数, name: "+userName;
    }

    @RequestMapping("/r7")
    public String r7(@RequestParam(value="name", required = false) String userName){
        return "接收到参数, name: "+userName;
    }

    @RequestMapping("/r8")
    public String r8(String[] array){
        return "接收到参数, array: "+ Arrays.toString(array);
    }
}
