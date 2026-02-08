package com.example.captcha.model;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="captcha")
@Data
public class CaptchaProperties {
    private Integer width = 200;
    private Integer height = 100;
    private Session session;
    @Data
    public static class Session{
        private String key;
        private String date;
    }
}
