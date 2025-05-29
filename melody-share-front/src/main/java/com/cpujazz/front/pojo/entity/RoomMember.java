package com.cpujazz.front.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * 房间成员表(RoomMember)表实体类
 *
 * @author makejava
 * @since 2025-05-20 10:19:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomMember {
    //编号    
    private Long id;
    //房间id    
    private Long roomId;
    //用户id    
    private Long userId;
    //加入时间    
    private Date joinTime;
}

