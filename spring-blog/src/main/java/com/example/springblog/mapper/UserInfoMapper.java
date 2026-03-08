package com.example.springblog.mapper;

import com.example.springblog.model.UserInfo;
import org.apache.ibatis.annotations.*;

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

    /**
     * 注册新用户
     */
    @Insert("insert into user (user_name, password, github_url) values (#{userName}, #{password}, #{githubUrl})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer insertUser(UserInfo userInfo);

    /**
     * 修改密码
     */
    @Update("update user set password = #{password} where id = #{id}")
    Integer updatePassword(@Param("id") Integer id, @Param("password") String password);

    /**
     * 更新用户信息
     */
    @Update("update user set github_url = #{githubUrl}, avatar = #{avatar} where id = #{id}")
    Integer updateUserInfo(UserInfo userInfo);
}
