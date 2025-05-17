package com.cpujazz.front.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * 用户表(Users)表实体类
 *
 * @author makejava
 * @since 2025-05-17 15:18:57
 */
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
    //角色：普通用户或管理员    
    private String role;
    //账号状态：1正常，0禁用    
    private Integer status;
    //注册时间    
    private Date createdAt;
    //更新时间    
    private Date updatedAt;
}

