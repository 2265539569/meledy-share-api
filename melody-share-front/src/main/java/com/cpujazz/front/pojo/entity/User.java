package com.cpujazz.front.pojo.entity;

import java.util.Date;

import com.cpujazz.front.enumeration.UserRole;
import com.cpujazz.front.enumeration.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    //主键ID    
    private Long id;
    //用户名    
    private String username;
    //加密密码    
    private String password;
    //昵称    
    private String nickname;
    //头像地址    
    private String avatar;
    //角色：管理员(ADMIN) 普通用户(USER)
    private String role;
    //账号状态：0禁用, 1正常
    private Integer status;
    //注册时间    
    private Date createdAt;
    //更新时间    
    private Date updatedAt;
}

