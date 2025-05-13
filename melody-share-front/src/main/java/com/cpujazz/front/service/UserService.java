package com.cpujazz.front.service;


import com.cpujazz.front.pojo.entity.User;
import com.cpujazz.front.pojo.result.ResponseResult;

public interface UserService {
    ResponseResult register(User user);

    ResponseResult login(User user);
}
