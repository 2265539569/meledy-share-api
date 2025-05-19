package com.cpujazz.front.pojo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto {
    //房间ID    
    private Long id;
    //房间名称    
    private String roomName;
    //房间描述    
    private String description;
    //房主ID（users.id）
    @JsonIgnore
    private Long ownerId;
    //房主名字
    private String ownerName;
    //是否为公开房间    
    private Integer isPublic;
    //房间人数
    private Integer count;
}

