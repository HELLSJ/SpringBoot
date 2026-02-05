package com.example.springioc.controller;

import com.example.springioc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class HelloController {
    //属性注⼊
    @Autowired
    private UserService userService;



//    private UserComponent userComponent;


    //构造⽅法注⼊
    public HelloController() {
    }

    @Autowired
    public HelloController(UserService userService) {
        this.userService = userService;
    }
//    @Autowired
//    public HelloController(UserService userService, UserComponent userComponent) {
//        this.userService = userService;
//        this.userComponent = userComponent;
//    }

    //Setter方法注入
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void sayHi(){
        userService.doService();
//        userComponent.doComponent();
        System.out.println("hi, helloController...");
    }
}
