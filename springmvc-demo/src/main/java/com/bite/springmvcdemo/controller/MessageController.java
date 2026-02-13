package com.bite.springmvcdemo.controller;

import com.bite.springmvcdemo.model.MessageInfo;
import com.bite.springmvcdemo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RequestMapping("/message")
@RestController

public class MessageController {
    @Autowired
    private MessageService messageService;

    @RequestMapping("/publish")
    public Boolean publish(MessageInfo messageInfo){
        //1.参数校验
        //2.存储数据
        if(!StringUtils.hasLength(messageInfo.getFrom())
                || !StringUtils.hasLength(messageInfo.getTo())
                || !StringUtils.hasLength(messageInfo.getSay())){
            return false;
        }
//        messageInfos.add(messageInfo);
        Integer result = messageService.publishMessage(messageInfo);
        if(result>0){
            return true;
        }

        return false;
    }
    /**
     * 获取留言信息
     */
    @RequestMapping("/getList")
    public List<MessageInfo> getList(){
        return messageService.getMessageList();
    }
}
