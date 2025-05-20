package com.cpujazz.front.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomAddDto {
    //房间名称
    private String roomName;
    //房间密码    
    private String roomPassword;
    //房间描述    
    private String description;
}

