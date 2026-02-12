package com.example.mybatisdemo.mapper;

import com.example.mybatisdemo.model.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserInfoMapperTest {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Test
    void getUserInfoAll() {
        System.out.println(userInfoMapper.getUserInfoAll());
    }


    @Test
    void getUserInfoAllByDeleteFlag() {
        System.out.println(userInfoMapper.getUserInfoAllByDeleteFlag(0));
    }

    @Test
    void getUserInfo2() {
        System.out.println(userInfoMapper.getUserInfo2(0, 1));
    }

    @Test
    void getUserInfo3() {
        System.out.println(userInfoMapper.getUserInfo3(4));
    }


    @Test
    void insert() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("zhaoliu");
        userInfo.setPassword("zhaoliu");
        userInfo.setAge(18);
        userInfo.setGender(1);
        Integer result = userInfoMapper.insert(userInfo);
        System.out.println("result: "+result + ",id: " +userInfo.getId()); //拿这个id
    }

    @Test
    void insert2() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("zhaoliu333");
        userInfo.setPassword("zhaoliu222");
        userInfo.setAge(18);
        userInfo.setGender(1);
        Integer result = userInfoMapper.insert2(userInfo);
        System.out.println("result: "+result);

    }

    @Test
    void delete() {
        System.out.println("删除数据: "+ userInfoMapper.delete(5));
    }

    @Test
    void update() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1);
        userInfo.setPassword("123456");
        userInfo.setAge(56);
        userInfo.setGender(2);
        Integer result = userInfoMapper.update(userInfo);
        System.out.println("更新影响行数: "+result);
    }

    @Test
    void selectUserInfos() {
        System.out.println(userInfoMapper.selectUserInfos());
    }

    @Test
    void selectUserInfos2() {
        System.out.println(userInfoMapper.selectUserInfos());
    }

    @Test
    void queryUserListByOrder() {
        System.out.println(userInfoMapper.queryUserListByOrder("asc"));
    }

    @Test
    void queryUserListByOrder1() {
        System.out.println(userInfoMapper.queryUserListByOrder1("asc"));
    }

    @Test
    void queryUserListByOrder2() {
        System.out.println(userInfoMapper.queryUserListByOrder2("asc"));
    }
}