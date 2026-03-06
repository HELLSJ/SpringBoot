package com.example.springtrans.service;

import com.example.springtrans.mapper.LogInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class LogService {
    @Autowired
    private LogInfoMapper logInfoMapper;
    @Transactional(propagation = Propagation.NESTED)
    public Integer insert(String userName, String op) {
        Integer result = logInfoMapper.insertLog(userName, op);
        try {
            int a = 10/0;
        }catch (Exception e){
            //回滚当前事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return result;
    }
}
