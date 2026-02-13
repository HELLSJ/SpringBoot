package com.example.mybatisdemo.mapper;

import com.example.mybatisdemo.model.UserInfo;
import com.example.mybatisdemo.service.UserService;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface UserInfoMapper {
    @Select("select * from userinfo")
    List<UserInfo> getUserInfoAll();

    @Select("select * from userinfo where delete_flag = #{deleteFlag}")
    List<UserInfo> getUserInfoAllByDeleteFlag(Integer deleteFlag);

    @Select("select * from userinfo where delete_flag = #{deleteFlag} and gender= #{gender}")
    List<UserInfo> getUserInfo2(@Param("deleteFlag") Integer deleteFlag, Integer gender);

    @Select("select * from userinfo where id = #{id}")
    UserInfo getUserInfo3(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into userinfo (username, password, age, gender) values (#{username}, #{password}, #{age}, #{gender})")
    Integer insert(UserInfo userInfo);

    @Insert("insert into userinfo (username, password, age, gender) " +
            "values (#{userInfo.username},#{userInfo.password},#{userInfo.age},#{userInfo.gender})")
    Integer insert2(@Param("userInfo") UserInfo userInfo);


    @Delete("delete from userinfo where id = #{id}")
    Integer delete(Integer id);

    @Update("update userinfo set password = #{password}, age = #{age}, gender = #{gender} where id = #{id}")
    Integer update(UserInfo userInfo);

    //select * from userinfo
    //问题：deleteFlag和数据库表格delete_flag名称对不上，会返回null
    /**
     * 解决方法1
     * 起别名
     * @return
     */
    @Select("SELECT id, username, password, age, gender, phone, " +
            "delete_flag as deleteFlag, create_time as createTime, update_time as updateTime " +
            "FROM `userinfo`")
    List<UserInfo> selectUserInfos();

    /**
     * 解决方法2
     * 指定结果映射关系
     * @return
     */
    @Results(id ="resultMap" , value = {
            @Result(column = "delete_flag", property = "deleteFlag"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    @Select("SELECT * FROM `userinfo`")
    List<UserInfo> selectUserInfos2();

    // 解决方法3见yml文件

    /**
     * SQL注入
     */
    @Select("select * from userinfo where username = #{userName}")
    UserInfo selectUserByName(String userName);

    @Select("select * from userinfo where username = '${userName}'")
    UserInfo selectUserByName2(String userName);

    @Select("select * from userinfo where username= '${username}' and password = '${password}'")
    UserInfo queryUserByNameAndPassword(String username, String password);

    //升序
    @Select("select * from userinfo order by id #{order}")
    List<UserInfo> queryUserListByOrder(String order);

    @Select("select * from userinfo order by id ${order}")
    List<UserInfo> queryUserListByOrder1(String order);

    @Select("select * from userinfo order by id asc")
    List<UserInfo> queryUserListByOrder2(String order);

    @Select("select * from userinfo where username like CONCAT('%',#{username},'%')")
    List<UserInfo> queryUserListByLike(String username);

    @Insert("<script>" +
            "insert into userinfo " +
            "<trim prefix='(' suffix=')' suffixOverrides=','> " +
            "    <if test='username!=null'>username,</if> " +
            "    <if test='password !=null'>password,</if> " +
            "    <if test='age !=null'>age, </if> " +
            "    <if test='gender != null'> gender</if> " +
            "</trim> " +
            " values " +
            "<trim prefix='(' suffix=')' suffixOverrides=','> " +
            "    <if test='username!=null'> #{username},</if> " +
            "    <if test='password !=null'>#{password},</if> " +
            "    <if test='age !=null'>#{age},</if> " +
            "    <if test='gender !=null'> #{gender}</if> " +
            "</trim>" +
            "</script>")
    Integer insertByCondtion(UserInfo userInfo);
}
