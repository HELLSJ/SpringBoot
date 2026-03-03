package com.example.springaop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class MyAspectDemo {
    @Around("@annotation(com.example.springaop.config.MyAspect)") //切点表达式声明选择什么注解，around声明这个注解作用的范围
    public Object doAround(ProceedingJoinPoint joinPoint){
        log.info("do around before...");
        Object o = null;
        try {
            o = joinPoint.proceed();
        } catch (Throwable e) {
            log.error("发生异常,e:", e);
        }
        log.info("do around after...");
        return o;
    }


    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object doAround2(ProceedingJoinPoint joinPoint){
        log.info("RequestMapping do around before...");
        Object o = null;
        try {
            o = joinPoint.proceed();
        } catch (Throwable e) {
            log.error("RequestMapping 发生异常,e:", e);
        }
        log.info("RequestMapping do around after...");
        return o;
    }
}
