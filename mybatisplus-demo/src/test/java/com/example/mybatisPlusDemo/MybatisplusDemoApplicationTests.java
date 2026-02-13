package com.example.mybatisPlusDemo;

import com.example.mybatisPlusDemo.mapper.UserInfoMapper;
import com.example.mybatisPlusDemo.model.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisplusDemoApplicationTests {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    void contextLoads() {
        //select cols from
//		System.out.println(userInfoMapper.selectList(null));
//		UserInfo userInfo = new UserInfo();
//		userInfo.setId(18);
//		userInfo.setAge(25);
//		userInfoMapper.updateById(userInfo);
        System.out.println(userInfoMapper.selectById(18));
    }
}
