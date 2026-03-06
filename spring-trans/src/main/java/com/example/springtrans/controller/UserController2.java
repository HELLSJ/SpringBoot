package com.example.springtrans.controller;

import com.example.springtrans.service.LogService;
import com.example.springtrans.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user2")
@RestController
public class UserController2 {
    @Autowired
    private UserService userService;
    @Autowired
    private LogService logService;

    @Transactional
    @RequestMapping("/register")
    public Boolean register(String userName, String password){
        /**
         * 用户表的插入和日志表的插入, 理应在Service完成
         * 为了方便教学, 咱们放在Controller里面完成
         */
        Integer result = userService.insert(userName, password);
        System.out.println("插入用户表, result: "+ result);
        //插入日志表
        Integer logResult = logService.insert(userName, "用户注册");
        System.out.println("插入日志表, result: "+ logResult);
        return true;
    }
}
