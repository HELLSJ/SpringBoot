package com.example.springblog.interceptor;

import com.example.springblog.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.获取token
        //2.校验token，判断是否放行
        String token = request.getHeader("user_token_header");
        log.info("从header中获取token:{}",token);
        Claims claims = JWTUtils.parseToken(token);
        if(claims == null){
            response.setStatus(401);
            return false;
        }
        return true;
    }
}
