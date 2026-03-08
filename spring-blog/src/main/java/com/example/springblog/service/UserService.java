package com.example.springblog.service;

import com.example.springblog.mapper.BlogMapper;
import com.example.springblog.mapper.UserInfoMapper;
import com.example.springblog.model.BlogInfo;
import com.example.springblog.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class UserService {
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    public UserInfo selectByName(String userName) {
        return userInfoMapper.selectByName(userName);
    }

    public UserInfo selectById(Integer userId) {
        return userInfoMapper.selectById(userId);
    }

    public UserInfo getAuthorInfo(Integer blogId) {
        BlogInfo blogInfo = blogMapper.selectById(blogId);
        if(blogInfo == null && blogInfo.getUserId() <= 0){
            log.error("博客不存在或博客信息不合法, blogId:{}", blogId);
            return null;
        }
        return userInfoMapper.selectById(blogInfo.getUserId());


    }
}
