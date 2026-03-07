package com.example.springblog.model;


import com.example.springblog.utils.DateUtils;
import lombok.Data;

import java.util.Date;

@Data
public class BlogInfo {
    private Integer id;
    private String title;
    private String content;
    private Integer userId;
    private Integer deleteFlag;
    private Date createTime;
    private Date updateTime;

    public String getCreateTime() {
        return DateUtils.dateFormat(createTime);
    }
}
