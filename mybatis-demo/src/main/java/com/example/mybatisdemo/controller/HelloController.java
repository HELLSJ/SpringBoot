package com.example.mybatisdemo.controller;

import com.example.mybatisdemo.model.UserInfo;
import com.example.mybatisdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/user")
@RestController
public class HelloController {

    @Autowired
    private UserService userService;
    @RequestMapping("/getAll")
    public List<UserInfo> getUserAll(){
        return userService.getUserAll();
    }
}
