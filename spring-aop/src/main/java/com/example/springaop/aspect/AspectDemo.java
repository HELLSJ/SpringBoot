package com.example.springaop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
//@Component
@Aspect
public class AspectDemo {
    @Pointcut("execution(* com.example.springaop.controller..*(..))")
//    @Pointcut("execution(* com.example.springaop.controller.*.*(..))")
    public void pt(){};

    @Before("pt()")
    public void doBefore(){
        log.info("AspectDemo do before....");
    }

    @After("pt()")
    public void doAfter(){
        log.info("AspectDemo do after....");
    }

    @Around("pt()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("AspectDemo do around before....");
        Object result = null;
        try{
            result = joinPoint.proceed();
        }catch (Exception e){
            log.error("do around执行目标函数,内部发生异常");
        }
        log.info("AspectDemo do around after...");
        return result;
    }

    @AfterReturning("pt()")
    public void doAfterReturning(){
        log.info("AspectDemo do after returning....");
    }

    @AfterThrowing("pt()")
    public void doAfterThrowing(){
        log.info("AspectDemo do after throwing....");
    }
}
