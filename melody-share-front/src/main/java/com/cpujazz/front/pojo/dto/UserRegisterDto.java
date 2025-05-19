package com.cpujazz.front.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDto {
    //用户名
    private String username;
    //加密密码    
    private String password;
    //昵称    
    private String nickname;
}

