package com.cpujazz.front.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.cpujazz.common.enumeration.ResponseMessage;
import com.cpujazz.front.mapper.UserMapper;
import com.cpujazz.front.pojo.dto.UserLoginDto;
import com.cpujazz.front.pojo.entity.User;
import com.cpujazz.front.pojo.result.ResponseResult;
import com.cpujazz.front.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Override
    public ResponseResult login(UserLoginDto userLoginDto) {
        User user = userMapper.selectByUserName(userLoginDto.getUsername());
        // 账号不存在
        if (Objects.isNull(user)) {
            return ResponseResult.error(ResponseMessage.ERROR_USER_NOT_EXISTS);
        }
        // 登录判断
        if (!Objects.equals(user.getPassword(), userLoginDto.getPassword())) {
            StpUtil.login(user.getId());
            return ResponseResult.success(ResponseMessage.SUCCESS_LOGIN);
        }
        return ResponseResult.error(ResponseMessage.ERROR_USER_LOGIN_FAILED);
    }

    @Override
    public ResponseResult info() {
        return null;
    }
}
