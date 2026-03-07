package com.example.springblog.config;


import com.example.springblog.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
@ResponseBody
@RestControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler
    public Result handle(Exception e){
        log.error("发生异常，e:"+e);
        return Result.fail("内部错误，请联系管理员");
    }

    @ExceptionHandler
    public Result handle(NoResourceFoundException e){
        log.error("文件不存在"+e.getMessage());
        return Result.fail("内部错误，请联系管理员");
    }
}