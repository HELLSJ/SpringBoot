package com.example.springblog.mapper;

import com.example.springblog.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserInfoMapper {
    /**
     * 根据用户名查询用户信息
     * @return
     */
    @Select("select * from user where delete_flag =0 and user_name = #{userName}")
    UserInfo selectByName(String userName);
    /**
     * 根据用户ID查询用户信息
     * @return
     */
    @Select("select * from user where delete_flag =0 and id = #{id}")
    UserInfo selectById(Integer id);
}
