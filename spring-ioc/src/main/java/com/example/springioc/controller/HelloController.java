package com.example.springioc.controller;

import org.springframework.stereotype.Controller;

@Controller
public class HelloController {
    public void sayHi(){
        System.out.println("hi, helloController");
    }
}
