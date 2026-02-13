package com.bite.book.controller;

import com.bite.book.model.UserInfo;
import com.bite.book.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

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
        UserInfo userInfo = userService.getUserInfoByName(userName);
        if(userInfo==null){
            return "用户不存在";
        }
        //如果查询到用户信息，校验密码信息
        if(!password.equals(userInfo.getPassword())){
            return "密码错误";
        }

        session.setAttribute("user_session_key", userInfo);
        return "";
    }
}
