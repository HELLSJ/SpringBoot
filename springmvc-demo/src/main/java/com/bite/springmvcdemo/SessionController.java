package com.bite.springmvcdemo;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RequestMapping("/session")
@RestController
public class SessionController {
    @RequestMapping("/getC")
    public String getCookie(HttpServletRequest request, HttpServletResponse response){
        //获取参数
        String name = request.getParameter("name");
        Cookie[] cookies = request.getCookies(); // 获取所有的Cookie
        if(cookies!=null){
            Arrays.stream(cookies).forEach(ck -> System.out.println(ck.getName()+":"+ck.getValue()));
        }
        return "获取Cookie";
    }

    @RequestMapping("/getC2")
    public String getCookie2(@CookieValue("bite") String bite){
        return "从Cookie中获得值, bite:"+bite;
    }

    @RequestMapping("/setSess")
    public String setSess(HttpServletRequest request){
        //从cookie中获取到了sessionID，根据session获取session对象，如果没有获取到，会创建一个session对象
        HttpSession session = request.getSession();
        session.setAttribute("name","zhangsan");
        return "设置session成功";
    }

    @RequestMapping("/getSess")
    public String getSess(HttpServletRequest request){
        //从cookie中获取到了sessionID，根据session获取session对象
        HttpSession session = request.getSession();
        String name = (String)session.getAttribute("name");
        return "从session中获取name："+name;
    }

}
