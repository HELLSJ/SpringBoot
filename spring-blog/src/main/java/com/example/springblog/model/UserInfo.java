package com.example.springblog.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfo {
    private Integer id;
    private String userName;
    private String password;
    private String githubUrl;
    private String avatar;
    private Integer deleteFlag;
    private Date createTime;
    private Date updateTime;
}
