package com.example.captcha.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/logger")
@RestController
public class LoggerController {
    private static Logger logger = LoggerFactory.getLogger("LoggerController"); //门面模式，提供统一的一个高层接口，用来访问子系统的一群接口
    //外观角色：系统对外的统一接口
    //子系统角色：对接外观角色
    @RequestMapping("/print")
    public String print(){
        logger.info("使用日志对象打印日志");
        System.out.println("使用sout打印日志");
        return "success";
    }

    @RequestMapping("/level")
    public String levelPrint(){
        logger.trace("========trace级别的日志==========");
        logger.debug("========debug级别的日志==========");
        logger.info("========info级别的日志==========");
        logger.warn("========warn级别的日志==========");
        logger.error("========error级别的日志==========");
        return "success";
    }
}
