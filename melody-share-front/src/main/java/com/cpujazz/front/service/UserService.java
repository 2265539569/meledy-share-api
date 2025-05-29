package com.cpujazz.front.service;

import com.cpujazz.front.pojo.dto.UserLoginDto;
import com.cpujazz.front.pojo.dto.UserRegisterDto;
import com.cpujazz.front.pojo.result.ResponseResult;

public interface UserService {
    ResponseResult login(UserLoginDto userLoginDto);

    ResponseResult info();

    ResponseResult register(UserRegisterDto userRegisterDto);

    ResponseResult roomInfo();
}
