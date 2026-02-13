package com.bite.springmvcdemo.model;


import lombok.*;

import java.util.Date;

@Data
public class MessageInfo {
    private Integer id;
    private String from;
    private String to;
    private String say;
    private Integer deleteFlag;
    private Date createTime;
    private Date updateTime;
    
    public void setMessage(String message) {
        this.say = message;
    }
    
    public String getMessage() {
        return this.say;
    }
}
