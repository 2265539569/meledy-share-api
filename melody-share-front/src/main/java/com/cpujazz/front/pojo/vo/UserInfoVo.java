package com.cpujazz.front.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoVo {
    //主键ID    
    private Long id;
    //昵称
    private String nickname;
    //头像地址    
    private String avatar;
}

