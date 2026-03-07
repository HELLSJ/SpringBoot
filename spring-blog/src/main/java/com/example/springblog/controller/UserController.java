package com.example.springblog.controller;

import com.example.springblog.model.Result;
import com.example.springblog.model.UserInfo;
import com.example.springblog.service.UserService;
import com.example.springblog.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
    public Result login(String username, String password){
        //1.参数校验
        //2.校验密码是否正确
        //3.密码正确
        //4.密码错误，返回错误信息
        if(!StringUtils.hasLength(username)||!StringUtils.hasLength(password)){
            return Result.fail("账号或密码不能为空");
        }
        //从数据库中查找用户
        //用户不存在
        UserInfo userInfo = userService.selectByName(username);
        if(userInfo == null){
            return Result.fail("用户不存在");
        }
        //密码错误
        if(!password.equals(userInfo.getPassword())){
            return Result.fail("密码错误");
        }
        //密码正确，返回token
        Map<String, Object> claim = new HashMap<>();
        claim.put("id", userInfo.getId());
        claim.put("userName", userInfo.getUserName());
        String token = JWTUtils.genJwtToken(claim);
        return Result.success(token);
    }

}
