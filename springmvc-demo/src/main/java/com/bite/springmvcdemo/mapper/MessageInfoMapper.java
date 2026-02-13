package com.bite.springmvcdemo.mapper;

import com.bite.springmvcdemo.model.MessageInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MessageInfoMapper {
    /**
     * 发布留言
     */

    @Insert("insert into message_info (`from`, `to`, `message`) values (#{from}, #{to}, #{say})")
    Integer insertMessage(MessageInfo messageInfo);
    /**
     * 获取留言列表
     */
    @Select("select * from message_info where delete_flag =0")
    @Results({
        @Result(property = "say", column = "message")
    })
    List<MessageInfo> selectAllList();

}
