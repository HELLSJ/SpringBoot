package com.bite.book.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {
    @RequestMapping("/login")
    public String login(String userName, String password, HttpSession session){
        //1.校验参数
        if(!StringUtils.hasLength(userName)||!StringUtils.hasLength(password)){
            return "用户名或者密码为空";
        }
        //模拟校验
//        if("admin".equals(userName)&&"admin".equals(password)){
//            return "";
//        }
//        return "密码错误";
//        if(!"admin".equals(userName) || !"admin".equals(password)){
//            return "密码错误";
//        }
        //根据用户名称，去数据库查询用户信息，如果未查询到，说明用户不存在
        //如果查询到用户信息，比对密码信息
        session.setAttribute("userName", userName);
        return "";
        //2.验证密码是否正确
        //3.返回相应结果
    }
}
