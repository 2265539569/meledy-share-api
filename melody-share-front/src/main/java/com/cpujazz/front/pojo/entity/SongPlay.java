package com.cpujazz.front.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongPlay {
    //编号    
    private Long id;
    //房间id    
    private Long roomId;
    //歌曲名字    
    private String songName;
    //歌手名    
    private String singer;
    //歌词    
    private String lyrics;
    //当前播放时间（秒）    
    private Integer currentTime;
    //是否正在播放（0暂停，1播放）    
    private Integer isPlaying;
    //更新时间    
    private Date updatedAt;


}

