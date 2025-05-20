package com.cpujazz.front.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * 用户表(User)表实体类
 *
 * @author makejava
 * @since 2025-05-20 10:18:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    //用户id    
    private Long id;
    //用户名    
    private String username;
    //用户密码    
    private String password;
    //用户昵称    
    private String nickname;
    //用户头像    
    private String avatar;
    //用户身份    
    private String role;
    //账号状态(0正常，1禁用)    
    private Integer status;
    //注册时间    
    private Date createdAt;
    //更新时间    
    private Date updatedAt;


}

