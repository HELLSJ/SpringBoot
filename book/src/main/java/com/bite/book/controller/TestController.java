package com.bite.book.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class TestController {
    @RequestMapping("/t1")
    public String t1(){
//        int result = 10/0;
        return "string";
    }

    @RequestMapping("/t2")
    public Integer t2(){
//        String str = null;
//        System.out.println(str.length());
        return 1;
    }

    @RequestMapping("/t3")
    public Boolean t3(){
        Integer[] integers = new Integer[]{1,2,3,4};
        System.out.println(integers[1]);
        return true;
    }
}
