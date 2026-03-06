package com.example.springblog.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

@Data
public class Result {
    //业务码
    private int code;
    //错误信息
    private String errMsg;
    //接口响应的数据
    private Object data;
}
