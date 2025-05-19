package com.cpujazz.front.mapper;

import com.cpujazz.front.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    User selectByUserName(@Param("username") String username);

    User selectByUserId(@Param("id") Long id);

    int insertOne(User user);
}
