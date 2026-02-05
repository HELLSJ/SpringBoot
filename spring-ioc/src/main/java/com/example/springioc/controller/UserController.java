package com.example.springioc.controller;

import com.example.springioc.model.UserInfo;
import com.example.springioc.service.UserService;
import jakarta.annotation.Resource;
import jakarta.annotation.Resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

@Controller("userController")
public class UserController {

    //使用@Qualifier注解：指定当前要注入的bean对象。 在@Qualifier的value属性中，指定注入的bean的名称。
    @Qualifier("userInfo")
    @Autowired
    private UserInfo userInfo;
//    @Autowired
//    private UserService user;

//    使用@Resource注解：是按照bean的名称进行注入。通过name属性指定要注入的bean的名称。jdk提供的
//    @Resource(name = "UCInfo")
//    private UserInfo user;


    public void sayHi(){
//        user.doService();
        System.out.println(userInfo);
        System.out.println("hi, user controller...");
    }
}
