package com.example.springblog.service;

import com.example.springblog.mapper.BlogMapper;
import com.example.springblog.mapper.UserInfoMapper;
import com.example.springblog.model.BlogInfo;
import com.example.springblog.model.UserInfo;
import com.example.springblog.utils.SecurityUtils;
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
     * 使用SecurityUtils对密码进行加密存储
     */
    public UserInfo register(UserInfo userInfo) {
        // 1. 参数校验
        if (!StringUtils.hasLength(userInfo.getUserName()) || !StringUtils.hasLength(userInfo.getPassword())) {
            log.warn("用户名或密码为空");
            return null;
        }
        
        // 2. 检查用户名是否已存在
        UserInfo existUser = userInfoMapper.selectByName(userInfo.getUserName());
        if (existUser != null) {
            log.warn("用户名已存在: {}", userInfo.getUserName());
            return null;
        }
        
        // 3. 对密码进行加密（盐值+MD5）
        String encryptedPassword = SecurityUtils.encrypt(userInfo.getPassword());
        userInfo.setPassword(encryptedPassword);
        
        // 4. 插入用户
        Integer result = userInfoMapper.insertUser(userInfo);
        if (result > 0) {
            log.info("用户注册成功: {}", userInfo.getUserName());
            // 返回时清除密码，避免泄露
            userInfo.setPassword(null);
            return userInfo;
        }
        return null;
    }

    /**
     * 用户登录验证
     * @param userName 用户名
     * @param password 明文密码
     * @return 验证成功返回用户信息，失败返回null
     */
    public UserInfo login(String userName, String password) {
        // 1. 参数校验
        if (!StringUtils.hasLength(userName) || !StringUtils.hasLength(password)) {
            return null;
        }
        
        // 2. 查询用户
        UserInfo userInfo = userInfoMapper.selectByName(userName);
        if (userInfo == null) {
            return null;
        }
        
        // 3. 验证密码（兼容明文密码和加密密码）
        boolean passwordValid = false;
        String storedPassword = userInfo.getPassword();
        
        if (storedPassword != null && storedPassword.length() == 64) {
            // 使用SecurityUtils验证加密密码
            passwordValid = SecurityUtils.verify(password, storedPassword);
        } else {
            // 兼容旧数据：明文密码直接比较
            passwordValid = password.equals(storedPassword);
        }
        
        if (!passwordValid) {
            log.warn("密码验证失败: {}", userName);
            return null;
        }
        
        // 4. 返回用户信息（清除密码）
        userInfo.setPassword(null);
        return userInfo;
    }

    /**
     * 修改密码
     * 需要验证旧密码，新密码会进行加密存储
     * 兼容明文密码和加密密码
     */
    public boolean changePassword(Integer userId, String oldPassword, String newPassword) {
        // 1. 参数校验
        if (userId == null || !StringUtils.hasLength(oldPassword) || !StringUtils.hasLength(newPassword)) {
            return false;
        }
        
        // 2. 查询用户
        UserInfo userInfo = userInfoMapper.selectById(userId);
        if (userInfo == null) {
            log.error("用户不存在: {}", userId);
            return false;
        }
        
        // 3. 验证旧密码（兼容明文密码和加密密码）
        boolean oldPasswordValid = false;
        String storedPassword = userInfo.getPassword();
        
        if (storedPassword != null && storedPassword.length() == 64) {
            // 使用SecurityUtils验证加密密码
            oldPasswordValid = SecurityUtils.verify(oldPassword, storedPassword);
        } else {
            // 兼容旧数据：明文密码直接比较
            oldPasswordValid = oldPassword.equals(storedPassword);
        }
        
        if (!oldPasswordValid) {
            log.warn("旧密码验证失败: {}", userId);
            return false;
        }
        
        // 4. 对新密码进行加密
        String encryptedNewPassword = SecurityUtils.encrypt(newPassword);
        
        // 5. 更新密码
        Integer result = userInfoMapper.updatePassword(userId, encryptedNewPassword);
        if (result > 0) {
            log.info("密码修改成功: {}", userId);
            return true;
        }
        return false;
    }

    /**
     * 更新用户信息
     */
    public boolean updateUserInfo(UserInfo userInfo) {
        if (userInfo == null || userInfo.getId() == null) {
            return false;
        }
        Integer result = userInfoMapper.updateUserInfo(userInfo);
        return result > 0;
    }
}
