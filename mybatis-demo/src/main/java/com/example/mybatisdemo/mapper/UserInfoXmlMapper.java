package com.example.mybatisdemo.mapper;

import com.example.mybatisdemo.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserInfoXmlMapper {
    Integer insert(UserInfo userInfo);
    Integer delete(Integer id);
    void update(UserInfo userInfo);

    List<UserInfo> selectAllUser();
    List<UserInfo> selectAllUser2();

    Integer count();

    Integer insertByCondtion(UserInfo userInfo);

    List<UserInfo> queryUserByCondition(UserInfo userInfo);

    Integer updateByCondition(UserInfo userInfo);

    Integer batchDelete(List<Integer> ids);

}
