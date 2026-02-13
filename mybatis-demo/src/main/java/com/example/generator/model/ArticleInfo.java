package com.example.generator.model;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleInfo {
    private Integer id;
    private String title;
    private String content;
    private Integer uid;
    private Integer deleteFlag;;
    private Date createTime;
    private Date updateTime;

    //用户相关的信息
    private String username;
    private Integer gender;
}
