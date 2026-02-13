package com.bite.springmvcdemo.service;

import com.bite.springmvcdemo.mapper.MessageInfoMapper;
import com.bite.springmvcdemo.model.MessageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageInfoMapper messageInfoMapper;
    public Integer publishMessage(MessageInfo messageInfo) {
        return messageInfoMapper.insertMessage(messageInfo);
    }

    public List<MessageInfo> getMessageList() {
        return messageInfoMapper.selectAllList();
    }
}
