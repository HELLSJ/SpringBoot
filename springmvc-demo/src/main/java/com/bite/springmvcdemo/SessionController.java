package com.bite.springmvcdemo;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

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

    /**
     * spring的方式获取cookie
     * @param bite
     * @return
     */
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

    /**
     * spring内置HttpSession
     * @param session
     * @return
     */
    @RequestMapping("/getSess2")
    public String getSess2(HttpSession session){
        String name = (String)session.getAttribute("name");
        return "从session中获取name："+name;
    }

    /**
     * spring再次封装
     * @param name
     * @return
     */
    @RequestMapping("/getSess3")
    public String getSess3(@SessionAttribute("name") String name){
        return "从session中获取name："+name;
    }
    @RequestMapping("/getHeader")
    public String getHeader(HttpServletRequest request){
        String userAgent = request.getHeader("User-Agent");
        return "从header中获取信息, userAgent:" + userAgent;
    }

    @RequestMapping("/getHeader2")
    public String getHeader2(@RequestHeader("User-Agent") String userAgent){
        return "从header中获取信息, userAgent:" + userAgent;
    }
}
