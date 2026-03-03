package com.example.springaop.controller;
//
import com.example.springaop.config.MyAspect;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController2 implements Iface{
    @MyAspect
    @RequestMapping("/test1")
    public void test() {
       System.out.println("测试测试");
    }
}
