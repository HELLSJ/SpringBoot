package com.example.mybatisdemo.mapper;

import com.example.mybatisdemo.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoXmlMapper {
    Integer insert(UserInfo userInfo);
}
