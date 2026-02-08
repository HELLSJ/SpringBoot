package com.example.captcha.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.example.captcha.model.CaptchaProperties;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;

@RequestMapping("/captcha")
@RestController
public class CaptchaController {
    private final static long session_valid_timeout = 60 * 1000;
    @Autowired
    private CaptchaProperties captchaProperties;
    /*
    生成验证码
    url: /captcha/get
    param: 无
    return: 图片内容
     */

    @RequestMapping("/get")
    public void getCaptcha(HttpSession session, HttpServletResponse response){
        //定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(captchaProperties.getWidth(), captchaProperties.getHeight());
        //图形验证码写出
        try {
            lineCaptcha.write(response.getOutputStream());
            //存储session
            session.setAttribute(captchaProperties.getSession().getKey(), lineCaptcha.getCode());
            session.setAttribute(captchaProperties.getSession().getDate(), new Date());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    校验验证码
    url: /captcha/check
    param: inputCode
    return: true/false
     */
    @RequestMapping("/check")
    public Boolean check(String inputCode, HttpSession session){
        //验证码生成的内容，和用户的输入进行比较
        if (!StringUtils.hasLength(inputCode)){
            return false;
        }
        String savedCode = (String) session.getAttribute(captchaProperties.getSession().getKey());
        Date savedDate = (Date) session.getAttribute(captchaProperties.getSession().getDate());
        if(inputCode.equalsIgnoreCase(savedCode)){
            //判断验证码是否过期
            if(savedDate == null && System.currentTimeMillis()-savedDate.getTime() < session_valid_timeout){
                return false;
            }
            return true;
        }
        return false;
    }
}
