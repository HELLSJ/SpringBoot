package com.example.springblog.controller;

import com.example.springblog.constants.Constants;
import com.example.springblog.model.Result;
import com.example.springblog.model.UserInfo;
import com.example.springblog.service.UserService;
import com.example.springblog.utils.JWTUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public Result login(String username, String password) {
        if (!StringUtils.hasLength(username) || !StringUtils.hasLength(password)) {
            return Result.fail("账号或密码不能为空");
        }
        UserInfo userInfo = userService.selectByName(username);
        if (userInfo == null) {
            return Result.fail("用户不存在");
        }
        if (!password.equals(userInfo.getPassword())) {
            return Result.fail("密码错误");
        }
        Map<String, Object> claim = new HashMap<>();
        claim.put(Constants.TOKEN_ID, userInfo.getId());
        claim.put(Constants.TOKEN_USERNAME, userInfo.getUserName());
        String token = JWTUtils.genJwtToken(claim);
        return Result.success(token);
    }

    /**
     * 用户注册
     */
    @RequestMapping("/register")
    public Result register(@RequestBody UserInfo userInfo) {
        if (!StringUtils.hasLength(userInfo.getUserName()) || !StringUtils.hasLength(userInfo.getPassword())) {
            return Result.fail("用户名或密码不能为空");
        }
        UserInfo result = userService.register(userInfo);
        if (result == null) {
            return Result.fail("注册失败，用户名可能已存在");
        }
        result.setPassword(null);
        return Result.success(result);
    }

    /**
     * 修改密码
     */
    @RequestMapping("/changePassword")
    public Result changePassword(String oldPassword, String newPassword, HttpServletRequest request) {
        if (!StringUtils.hasLength(oldPassword) || !StringUtils.hasLength(newPassword)) {
            return Result.fail("旧密码和新密码不能为空");
        }
        String token = request.getHeader(Constants.REQUEST_HEADER_TOKEN);
        Integer userId = JWTUtils.getIdByToken(token);
        if (userId == null) {
            return Result.fail("用户未登录");
        }
        boolean result = userService.changePassword(userId, oldPassword, newPassword);
        if (!result) {
            return Result.fail("修改密码失败，旧密码错误");
        }
        return Result.success(true);
    }

    /**
     * 更新用户信息
     */
    @RequestMapping("/updateInfo")
    public Result updateInfo(@RequestBody UserInfo userInfo, HttpServletRequest request) {
        String token = request.getHeader(Constants.REQUEST_HEADER_TOKEN);
        Integer userId = JWTUtils.getIdByToken(token);
        if (userId == null) {
            return Result.fail("用户未登录");
        }
        userInfo.setId(userId);
        boolean result = userService.updateUserInfo(userInfo);
        if (!result) {
            return Result.fail("更新用户信息失败");
        }
        UserInfo updatedUser = userService.selectById(userId);
        updatedUser.setPassword(null);
        return Result.success(updatedUser);
    }

    /**
     * 获取当前登录用户信息
     */
    @RequestMapping("/getUserInfo")
    public UserInfo getUserInfo(HttpServletRequest request) {
        String token = request.getHeader(Constants.REQUEST_HEADER_TOKEN);
        Integer userId = JWTUtils.getIdByToken(token);
        if (userId == null) {
            return null;
        }
        UserInfo userInfo = userService.selectById(userId);
        return userInfo;
    }

    @RequestMapping("/getAuthorInfo")
    public UserInfo getAuthorInfo(Integer blogId) {
        if (blogId <= 0) {
            return null;
        }
        UserInfo userInfo = userService.getAuthorInfo(blogId);
        return userInfo;
    }
}
