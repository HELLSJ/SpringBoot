package com.bite.springmvcdemo;


import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RequestMapping("/message")
@RestController

public class MessageController {
    private List<MessageInfo> messageInfos = new ArrayList<>();
    @RequestMapping("/publish")
    public Boolean publish(MessageInfo messageInfo){
        //1.参数校验
        //2.存储数据
        if(!StringUtils.hasLength(messageInfo.getFrom())
                || !StringUtils.hasLength(messageInfo.getTo())
                || !StringUtils.hasLength(messageInfo.getSay())){
            return false;
        }
        messageInfos.add(messageInfo);
        return true;
    }
    /**
     * 获取留言信息
     */
    @RequestMapping("/getList")
    public List<MessageInfo> getList(){
        return messageInfos;
    }
}
