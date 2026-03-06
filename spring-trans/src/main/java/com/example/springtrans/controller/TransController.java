package com.example.springtrans.controller;


import com.example.springtrans.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequestMapping("/trans")
@RestController
public class TransController {
    @Autowired
    private UserService userService;

    /**
     * 对异常进行捕获, 事务会正常提交
     * 不捕获, 事务会回滚
     */
    @Transactional
    @RequestMapping("/register")
    public Boolean register(String userName, String password){
        Integer result = userService.insert(userName, password);
        System.out.println("插入用户表, result: "+ result);
        try {
            int a = 10/0;
        }catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }

    /**
     * 重新把异常抛出去, 事务回滚
     */
    @Transactional
    @RequestMapping("/r2")
    public Boolean r2(String userName, String password){
        Integer result = userService.insert(userName, password);
        System.out.println("插入用户表, result: "+ result);
        try {
            int a = 10/0;
        }catch (Exception e){
            throw e;
        }

        return true;
    }

    /**
     * 手动把事务进行回滚
     */
    @Transactional
    @RequestMapping("/r3")
    public Boolean r3(String userName, String password){
        Integer result = userService.insert(userName, password);
        System.out.println("插入用户表, result: "+ result);
        try {
            int a = 10/0;
        }catch (Exception e){
            //手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return true;
    }

    /**
     *  事务提交
     */
    @Transactional
    @RequestMapping("/r4")
    public Boolean r4(String userName, String password) throws IOException {
        Integer result = userService.insert(userName, password);
        System.out.println("插入用户表, result: "+ result);
        if (true){
            throw new IOException();
        }

        return true;
    }
    /**
     *  事务回滚
     */
    @SneakyThrows
    @Transactional
    @RequestMapping("/r5")
    public Boolean r5(String userName, String password) {
        Integer result = userService.insert(userName, password);
        System.out.println("插入用户表, result: "+ result);
        if (true){
            throw new IOException();
        }

        return true;
    }

    /**
     *  事务回滚
     */
    @Transactional
    @RequestMapping("/r6")
    public Boolean r6(String userName, String password) throws IOException {
        Integer result = userService.insert(userName, password);
        System.out.println("插入用户表, result: "+ result);
        if (true){
            throw new RuntimeException();
        }

        return true;
    }

    /**
     *  事务回滚
     */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
    @RequestMapping("/r7")
    public Boolean r7(String userName, String password) throws IOException {
        Integer result = userService.insert(userName, password);
        System.out.println("插入用户表, result: "+ result);
        if (true){
            throw new IOException();
        }

        return true;
    }
}
