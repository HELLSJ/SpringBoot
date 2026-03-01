package com.example.springaop.controller;

import com.example.springaop.config.MyAspect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/test")
@RestController
public class TestController {

    @MyAspect
    @RequestMapping("/t1")
    public String t1(){
        log.info("执行t1方法...");
        return "t1";
    }

    @RequestMapping("/t2")
    public String t2(){
        log.info("执行t2方法...");
//        int a = 10/0;
        return "t2";
    }

}
