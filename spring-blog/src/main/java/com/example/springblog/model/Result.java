package com.example.springblog.model;

import com.example.springblog.enums.ResultStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

@Data
public class Result<T> {
    //业务码
    private ResultStatus code;
    //错误信息
    private String errMsg;
    //接口响应的数据
    private T data;

    public static <T> Result<T> success(T data){
        Result result = new Result();
        result.setCode(ResultStatus.SUCCESS);
        result.setErrMsg("");
        result.setData(data);
        return result;
    }
    public static <T> Result<T> fail(String errMsg){
        Result result = new Result();
        result.setCode(ResultStatus.FAIL);
        result.setErrMsg(errMsg);
        result.setData(null);
        return result;
    }
}
