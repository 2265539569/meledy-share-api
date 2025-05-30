package com.cpujazz.front.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * 聊天记录表(ChatRecord)表实体类
 *
 * @author makejava
 * @since 2025-05-20 10:19:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRecord {
    //编号    
    private Long id;
    //房间id    
    private Long roomId;
    //用户id    
    private Long userId;
    //用户发送的消息    
    private String chatMessage;
    //发送时间    
    private Date sendTime;
}

