package com.example.springblog.service;

import com.example.springblog.mapper.BlogMapper;
import com.example.springblog.mapper.UserInfoMapper;
import com.example.springblog.model.BlogInfo;
import com.example.springblog.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
        if (blogInfo == null || blogInfo.getUserId() <= 0) {
            log.error("博客不存在或博客信息不合法, blogId:{}", blogId);
            return null;
        }
        return userInfoMapper.selectById(blogInfo.getUserId());
    }

    /**
     * 用户注册
     */
    public UserInfo register(UserInfo userInfo) {
        if (!StringUtils.hasLength(userInfo.getUserName()) || !StringUtils.hasLength(userInfo.getPassword())) {
            return null;
        }
        UserInfo existUser = userInfoMapper.selectByName(userInfo.getUserName());
        if (existUser != null) {
            return null;
        }
        Integer result = userInfoMapper.insertUser(userInfo);
        if (result > 0) {
            return userInfo;
        }
        return null;
    }

    /**
     * 修改密码
     */
    public boolean changePassword(Integer userId, String oldPassword, String newPassword) {
        UserInfo userInfo = userInfoMapper.selectById(userId);
        if (userInfo == null || !userInfo.getPassword().equals(oldPassword)) {
            return false;
        }
        Integer result = userInfoMapper.updatePassword(userId, newPassword);
        return result > 0;
    }

    /**
     * 更新用户信息
     */
    public boolean updateUserInfo(UserInfo userInfo) {
        if (userInfo.getId() == null) {
            return false;
        }
        Integer result = userInfoMapper.updateUserInfo(userInfo);
        return result > 0;
    }
}
