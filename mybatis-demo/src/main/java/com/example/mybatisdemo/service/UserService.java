package com.example.mybatisdemo.service;

import com.example.mybatisdemo.mapper.UserInfoMapper;
import com.example.mybatisdemo.model.UserInfo;
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
