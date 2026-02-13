package com.example.generator.service;

import com.example.generator.mapper.UserInfoMapper;
import com.example.generator.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    public List<UserInfo> getUserAll() {
        return userInfoMapper.getUserInfoAll();
    }
}
