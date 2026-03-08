package com.example.springblog.mapper;

import com.example.springblog.model.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserInfoMapperTest {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    void selectByName() {
        // 测试存在的用户
        UserInfo existingUser = userInfoMapper.selectByName("zhangsan");
        assertNotNull(existingUser);
        assertEquals("zhangsan", existingUser.getUserName());
        System.out.println("查询存在用户结果: " + existingUser);
        
        // 测试不存在的用户
        UserInfo nonExistingUser = userInfoMapper.selectByName("nonexistentuser" + System.currentTimeMillis());
        assertNull(nonExistingUser);
        System.out.println("查询不存在用户结果: " + nonExistingUser);
    }

    @Test
    void selectById() {
        // 测试存在的用户ID
        UserInfo existingUser = userInfoMapper.selectById(1);
        assertNotNull(existingUser);
        assertEquals(1, existingUser.getId());
        System.out.println("查询存在用户ID结果: " + existingUser);
        
        // 测试不存在的用户ID
        UserInfo nonExistingUser = userInfoMapper.selectById(999999);
        assertNull(nonExistingUser);
        System.out.println("查询不存在用户ID结果: " + nonExistingUser);
    }

    @Test
    void insertUser() {
        // 创建测试用户
        UserInfo userInfo = new UserInfo();
        String testUserName = "testuser";
        userInfo.setUserName(testUserName);
        userInfo.setPassword("123456");
        userInfo.setGithubUrl("https://github.com/test");
        
        // 执行插入
        int result = userInfoMapper.insertUser(userInfo);
        System.out.println("插入结果: " + result);
        System.out.println("新用户ID: " + userInfo.getId());
        
        // 验证插入是否成功
        assertNotNull(userInfo.getId());
        assertTrue(result > 0);
        
        // 验证用户是否真的被插入
        UserInfo insertedUser = userInfoMapper.selectByName(testUserName);
        assertNotNull(insertedUser);
        assertEquals(testUserName, insertedUser.getUserName());
        assertEquals("https://github.com/test", insertedUser.getGithubUrl());
    }

    @Test
    void updatePassword() {
        int userId = 1; // 假设ID为1的用户存在
        String originalPassword = "123456";
        String newPassword = "654321";
        
        // 执行密码修改
        int result = userInfoMapper.updatePassword(userId, newPassword);
        System.out.println("修改密码结果: " + result);
        
        // 验证修改是否成功
        assertTrue(result > 0);
        
        // 验证密码是否真的被修改了
        UserInfo userInfo = userInfoMapper.selectById(userId);
        assertEquals(newPassword, userInfo.getPassword());
        
        // 改回原密码，避免影响其他测试
        userInfoMapper.updatePassword(userId, originalPassword);
        UserInfo restoredUser = userInfoMapper.selectById(userId);
        assertEquals(originalPassword, restoredUser.getPassword());
        System.out.println("密码已恢复为原密码");
    }

    @Test
    void updateUserInfo() {
        int userId = 1; // 假设ID为1的用户存在
        
        // 先获取原用户信息，用于后续恢复
        UserInfo originalUser = userInfoMapper.selectById(userId);
        assertNotNull(originalUser);
        String originalGithubUrl = originalUser.getGithubUrl();
        String originalAvatar = originalUser.getAvatar();
        
        // 创建更新对象
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userId);
        userInfo.setGithubUrl("https://github.com/updated");
        userInfo.setAvatar("https://example.com/avatar.jpg");
        
        // 执行更新
        int result = userInfoMapper.updateUserInfo(userInfo);
        System.out.println("更新用户信息结果: " + result);
        
        // 验证更新是否成功
        assertTrue(result > 0);
        
        // 验证信息是否真的被更新了
        UserInfo updatedUser = userInfoMapper.selectById(userId);
        assertEquals("https://github.com/updated", updatedUser.getGithubUrl());
        assertEquals("https://example.com/avatar.jpg", updatedUser.getAvatar());
    }
}