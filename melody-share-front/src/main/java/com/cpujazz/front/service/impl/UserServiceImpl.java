package com.cpujazz.front.service.impl;

import com.cpujazz.front.enumeration.ResponseMessage;
import com.cpujazz.front.enumeration.UserRole;
import com.cpujazz.front.enumeration.UserStatus;
import com.cpujazz.front.mapper.UserMapper;
import com.cpujazz.front.pojo.entity.User;
import com.cpujazz.front.pojo.result.ResponseResult;
import com.cpujazz.front.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Override
    public ResponseResult register(User user) {
        // 如果账户存在直接返回
        if (userMapper.selectUserByUsername(user.getUsername()) != null) {
            ResponseResult.error(ResponseMessage.ERROR_USER_EXISTS);
        }

        // 新账号初始化
        user.setRole(UserRole.USER.getFlag());
        user.setStatus(UserStatus.ENABLED.getCode());
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());

        if (userMapper.insertUser(user) > 0) {
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult login(User loginUser) {
        User user = userMapper.selectUserByUsername(loginUser.getUsername());
        // 如果账户存在就登录
        if (user == null) {
            return ResponseResult.error(ResponseMessage.ERROR_USER_EXISTS);
        }
        if (!user.getPassword().equals(loginUser.getPassword())) {
            return ResponseResult.error(ResponseMessage.ERROR_USER_LOGIN_FAILED);
        }
        return ResponseResult.success("jwt_info");
    }


}
