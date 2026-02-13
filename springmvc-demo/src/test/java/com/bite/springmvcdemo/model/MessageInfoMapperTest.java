package com.bite.springmvcdemo.model;

import com.bite.springmvcdemo.mapper.MessageInfoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MessageInfoMapperTest {
    @Autowired
    private MessageInfoMapper messageInfoMapper;


    @Test
    void insertMessage() {
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setFrom("aaa");
        messageInfo.setTo("bbb");
        messageInfo.setSay("ccc");
        messageInfoMapper.insertMessage(messageInfo);
    }

    @Test
    void selectAllList() {
        System.out.println(messageInfoMapper.selectAllList());
    }
}