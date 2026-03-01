package com.bite.book.aspect;


//1.切点：提供一组规则，告诉程序哪些方法进行功能增强
//2.连接点 joint point：被告诉的方法 com.bite.book.controller.*.*(..) 这里就是BookController里面所有的方法都是连接点
//3.通知 advice：连接点具体要做的事情，有哪些共性功能
//4.切面：切点+通知

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Component //被spring管理
@Aspect //切面的类型
public class TimeRecordAspect {
    /**
     * 记录耗时
     */
    //@Around：环绕通知，表示侵入程序的时机，在目标函数执行前后都会执行，
    @Around("execution(* com.bite.book.controller.*.*(..))") //execution里面是切点表达式，第一个*表示controller里面所有的类，第二个*表示类里面所有的方法
    public Object timeRecord(ProceedingJoinPoint joinPoint) throws Throwable {
        //记录开始时间
        long start = System.currentTimeMillis();
        //执行目标方法
        Object proceed = joinPoint.proceed();
        //记录结束时间
        long end = System.currentTimeMillis();
        //日志打印耗时
        log.info("耗时时间: "+ (end-start) + "ms");
        return proceed;
    }
}
