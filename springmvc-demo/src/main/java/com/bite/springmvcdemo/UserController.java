package com.bite.springmvcdemo;


import jakarta.servlet.http.HttpSession;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RequestMapping("/user")
@RestController
public class UserController {
    @RequestMapping("/login")
    public Boolean login(String userName, String passWord, HttpSession session){
        //校验参数
        if(StringUtils.hasLength(userName)||!StringUtils.hasLength(passWord)){
            return false;
        }
        //判断密码是否正确，暂时没有数据库，先写死
        //上面已经做了判空的处理，userName不会为null,这是一种习惯
        if("admin".equals(userName) && "admin".equals(passWord)){
            //设置session信息
            session.setAttribute("userName", userName);
            return true;
        }
        return false;
    }
    @RequestMapping("/index")
    public String getUserName(@SessionAttribute("userName") String userName){
        return userName;
    }
}
