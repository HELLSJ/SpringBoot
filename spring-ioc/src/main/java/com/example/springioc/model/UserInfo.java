package com.example.springioc.model;

import lombok.Data;


@Data
public class UserInfo {
    private Integer id;
    private String name;
    private Integer age;

    public UserInfo() {
    }

    public UserInfo(String name) {
        this.name = name;
    }
}
