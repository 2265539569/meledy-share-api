package com.cpujazz.front.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * 房间表(Room)表实体类
 *
 * @author makejava
 * @since 2025-05-20 10:19:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    //房间id    
    private Long id;
    //房间名称    
    private String roomName;
    //房间密码    
    private String roomPassword;
    //房间描述    
    private String description;
    //房主id    
    private Long ownerId;
    //是否为公开房间(0公开，1私有)    
    private Integer isPublic;
    //创建时间    
    private Date createdAt;
    //更新时间    
    private Date updatedAt;


}

