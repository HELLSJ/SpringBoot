package com.example.mybatisdemo.mapper;

import com.example.mybatisdemo.model.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserInfoXmlMapperTest {

    @Autowired
    private UserInfoXmlMapper userInfoXmlMapper;

    @Test
    void insert() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("zhaoliu");
        userInfo.setPassword("zhaoliu");
        userInfo.setAge(12);
        userInfo.setGender(1);
        userInfoXmlMapper.insert(userInfo);
    }

    @Test
    void delete() {
        userInfoXmlMapper.delete(5);
    }

    @Test
    void update() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1);
        userInfo.setPassword("admin");
        userInfoXmlMapper.update(userInfo);
    }

    @Test
    void selectAllUser() {
        System.out.println(userInfoXmlMapper.selectAllUser());
    }

    @Test
    void count() {
        System.out.println("======================"+userInfoXmlMapper.count());
    }

    @Test
    void selectAllUser2() {
        System.out.println(userInfoXmlMapper.selectAllUser2());
    }

    @Test
    void insertByCondtion() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("zhaoliu444");
        userInfo.setPassword("zhaoliu444");
        userInfo.setAge(12);
//        userInfo.setGender(1);
        userInfoXmlMapper.insertByCondtion(userInfo);
    }

    @Test
    void queryUserByCondition() {
        UserInfo userInfo = new UserInfo();
        userInfo.setAge(12);
        userInfo.setGender(1);
        userInfo.setDeleteFlag(0);
        System.out.println(userInfoXmlMapper.queryUserByCondition(userInfo));
    }


    @Test
    void updateByCondition() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(5);
        userInfo.setPassword("12321414");
        userInfo.setGender(3);
//        userInfo.setAge(18);
        System.out.println(userInfoXmlMapper.updateByCondition(userInfo));
    }
//
//    @Test
//    void batchDelete() {
//        List<Integer> ids = Arrays.asList(new Integer[]{22,23,24,25});
//        userInfoXmlMapper.batchDelete(ids);
//    }
}