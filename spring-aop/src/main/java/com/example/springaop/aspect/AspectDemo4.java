package com.example.springaop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(25)
@Slf4j
//@Component
@Aspect
public class AspectDemo4 {

    @Before("com.example.springaop.aspect.AspectDemo.pt()")
    public void doBefore(){
        log.info("AspectDemo4 do before....");
    }

    @After("com.example.springaop.aspect.AspectDemo.pt()")
    public void doAfter(){
        log.info("AspectDemo4 do after....");
    }

}
