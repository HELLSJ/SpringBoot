package com.example.springioc.controller;

import com.example.springioc.model.DbType;
import com.example.springioc.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReadymlController {
    @Value("${mykey}")
    private String key;

    @Value("${server.port}")
    private Integer port;

    @Value("${mykey.key1}")
    private String key1;

    @Value("${string.str1}")
    private String str1;
    @Value("${string.str2}")
    private String str2;
    @Value("${string.str3}")
    private String str3;


    @Autowired
    private Student student;

    @Autowired
    private DbType dbType;

    @RequestMapping("/readyml")
    public String readYml(){
        return "从yml中获取配置文件: "+ key;
    }

    @RequestMapping("/readyml2")
    public String readYml2(){
        return "从yml中获取配置文件: "+ port;
    }

    @RequestMapping("/readyml3")
    public String readYml3(){
        return "从yml中获取配置文件: "+ key1;
    }

    @RequestMapping("/readyml4")
    public String readYml4(){
        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str3);
        return "从yml中获取配置文件.";
    }
    // 读取对象
    @RequestMapping("/readStudent")
    public String readStudent(){
        return "从yml中获取配置文件Student:"+ student;
    }
    //读取集合
    @RequestMapping("/readDbtype")
    public String readDbtype(){
        return "从yml中获取配置文件Student:"+ dbType+",size:"+dbType.getName().size();
    }

}
