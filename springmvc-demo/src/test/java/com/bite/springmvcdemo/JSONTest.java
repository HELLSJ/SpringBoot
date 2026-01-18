package com.bite.springmvcdemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONTest {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonstr = "{\"id\":12,\"name\":\"zhangsan\",\"age\":5}";
        Student student = objectMapper.readValue(jsonstr, Student.class);
        System.out.println(student);
        String s = objectMapper.writeValueAsString(student);
        System.out.println(s);
    }
}
