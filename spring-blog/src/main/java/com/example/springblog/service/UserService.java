package com.example.springblog.service;

import com.example.springblog.mapper.UserInfoMapper;
import com.example.springblog.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    public UserInfo selectByName(String userName) {
        return userInfoMapper.selectByName(userName);
    }

    public UserInfo selectById(Integer userId) {
        return userInfoMapper.selectById(userId);
    }
}
