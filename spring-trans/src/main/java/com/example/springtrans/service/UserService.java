package com.example.springtrans.service;

import com.example.springtrans.mapper.UserInfoMapper;
import com.example.springtrans.model.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Transactional(propagation = Propagation.NESTED)
    public Integer insert(String userName, String password) {
        return userInfoMapper.insert(userName, password);
    }
}
