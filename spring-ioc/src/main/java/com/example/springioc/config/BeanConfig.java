package com.example.springioc.config;

import com.example.springioc.model.UserInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class BeanConfig {
//    @Primary
    @Bean
    public UserInfo userInfo(){
        return new UserInfo("zhangsan");
    }
//    @Bean
//    public UserInfo userInfo1(){
//        return new UserInfo("lisi");
//    }
//    @Bean
//    public UserInfo UCInfo(){
//        return new UserInfo("lisi222");
//    }
}
