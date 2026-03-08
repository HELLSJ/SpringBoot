package com.example.springblog.controller;

import com.example.springblog.constants.Constants;
import com.example.springblog.model.Result;
import com.example.springblog.model.UserInfo;
import com.example.springblog.service.UserService;
import com.example.springblog.utils.JWTUtils;
import jakarta.servlet.http.HttpServletRequest;
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
        claim.put(Constants.TOKEN_ID, userInfo.getId());
        claim.put(Constants.TOKEN_USERNAME, userInfo.getUserName());
        String token = JWTUtils.genJwtToken(claim);
        return Result.success(token);
    }

    /**
     * 获取当前登录用户信息
     * @return
     */
    @RequestMapping("/getUserInfo")
    public UserInfo getUserInfo(HttpServletRequest request){
        //获取token
        String token = request.getHeader(Constants.REQUEST_HEADER_TOKEN);
        //从token中获取登录用户ID
        Integer userId = JWTUtils.getIdByToken(token);
        if(userId==null){
            //用户未登录
            return null;
        }
        UserInfo userInfo = userService.selectById(userId);
        return userInfo;
    }

    @RequestMapping("/getAuthorInfo")
    public UserInfo getAuthorInfo(Integer blogId){
        //1.根据博客ID，获取作者ID
        //2.根据作者ID，获取作者信息
        if(blogId<=0){
            return null;
        }
        UserInfo userInfo = userService.getAuthorInfo(blogId);
        return userInfo;
    }
}
