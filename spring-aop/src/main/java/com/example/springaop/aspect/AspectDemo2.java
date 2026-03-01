package com.example.springaop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Slf4j
//@Component
@Aspect
public class AspectDemo2 {

    @Before("com.example.springaop.aspect.AspectDemo.pt()")
    public void doBefore(){
        log.info("AspectDemo2 do before....");
    }

    @After("com.example.springaop.aspect.AspectDemo.pt()")
    public void doAfter(){
        log.info("AspectDemo2 do after....");
    }

}
