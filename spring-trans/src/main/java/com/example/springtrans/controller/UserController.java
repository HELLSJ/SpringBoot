package com.example.springtrans.controller;

import com.example.springtrans.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;
    @Autowired
    private TransactionDefinition transactionDefinition;
    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public Boolean register(String userName, String password){
        //开启事务
        TransactionStatus transaction = dataSourceTransactionManager.getTransaction(transactionDefinition);
        Integer result = userService.insert(userName, password);
        System.out.println("插入用户表, result: "+ result);
        //回滚事务
//        dataSourceTransactionManager.rollback(transaction);
        //提交事务
        dataSourceTransactionManager.commit(transaction);
        return true;
    }
}
