package com.example.springtrans.mapper;

import com.example.springtrans.model.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface UserInfoMapper {

    @Insert("insert into user_info (user_name, password) values(#{userName}, #{password})")
    Integer insert(String userName, String password);
}
