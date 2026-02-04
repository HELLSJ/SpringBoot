package com.example.springioc.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    public void doConfig(){
        System.out.println("do config...");
    }

}
