package com.example.springblog.interceptor;

import com.example.springblog.constants.Constants;
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
        String requestURI = request.getRequestURI();
        // 登录请求直接放行
        if ("/user/login".equals(requestURI)) {
            return true;
        }

        //1.获取token
        //2.校验token，判断是否放行
        String token = request.getHeader(Constants.REQUEST_HEADER_TOKEN);
        log.info("从header中获取token:{}",token);
        // 检查token是否存在
        if (token == null || token.isEmpty()) {
            log.error("token为空或不存在");
            response.setStatus(401);
            return false;
        }
        Claims claims = JWTUtils.parseToken(token);
        if(claims == null){
            response.setStatus(401);
            return false;
        }
        return true;
    }
}
