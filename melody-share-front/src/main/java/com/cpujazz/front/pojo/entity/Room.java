package com.cpujazz.front.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    //房间ID    
    private Long id;
    //房间名称    
    private String roomName;
    //房间密码    
    private String roomPass;
    //房间描述    
    private String description;
    //房主ID（users.id）    
    private Long ownerId;
    //是否为公开房间    
    private Integer isPublic;
    //房间状态    
    private String status;
    //创建时间    
    private Date createdAt;
    //更新时间    
    private Date updatedAt;
}

