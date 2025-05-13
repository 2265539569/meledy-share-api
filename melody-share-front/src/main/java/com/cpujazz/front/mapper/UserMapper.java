package com.cpujazz.front.mapper;

import com.cpujazz.front.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
     // 插入用户
     int insertUser(User user);
     // 通过用户名获取用户
     User selectUserByUsername(String username);
}
