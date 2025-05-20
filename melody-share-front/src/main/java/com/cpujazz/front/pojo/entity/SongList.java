package com.cpujazz.front.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongList {
    //编号    
    private Long id;
    //房间id    
    private Long roomId;
    //点赞数    
    private Integer like;
    //歌曲名    
    private String songName;
    //歌曲专辑    
    private String songAlbum;
    //歌手名    
    private String singer;
    //歌曲时长    
    private Integer songDuration;
    //点歌人    
    private Long byUserId;
    //点歌时间    
    private Date byUserTime;


}

