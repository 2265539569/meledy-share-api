package com.cpujazz.front.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.cpujazz.common.enumeration.ResponseMessage;
import com.cpujazz.common.enumeration.UserRole;
import com.cpujazz.common.enumeration.UserStatus;
import com.cpujazz.front.mapper.UserMapper;
import com.cpujazz.front.pojo.dto.UserLoginDto;
import com.cpujazz.front.pojo.dto.UserRegisterDto;
import com.cpujazz.front.pojo.entity.User;
import com.cpujazz.front.pojo.result.ResponseResult;
import com.cpujazz.front.pojo.vo.UserInfoVo;
import com.cpujazz.front.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

import static cn.dev33.satoken.stp.StpUtil.getLoginIdAsLong;

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
        if (user.getPassword().equals(userLoginDto.getPassword())) {
            StpUtil.login(user.getId());
            return ResponseResult.success(ResponseMessage.SUCCESS_LOGIN);
        }
        return ResponseResult.error(ResponseMessage.ERROR_USER_LOGIN_FAILED);
    }

    @Override
    public ResponseResult info() {
        Long userId = getLoginIdAsLong();
        User user = userMapper.selectByUserId(userId);
        UserInfoVo userInfoVo = BeanUtil.toBean(user, UserInfoVo.class);
        return ResponseResult.success(userInfoVo);
    }

    @Override
    public ResponseResult register(UserRegisterDto userRegisterDto) {
        // 检查用户是否存在
        User user = userMapper.selectByUserName(userRegisterDto.getUsername());
        if (!Objects.isNull(user)) {
            return ResponseResult.error(ResponseMessage.ERROR_USER_EXISTS);
        }
        user = BeanUtil.toBean(userRegisterDto, User.class);
        user.setUpdatedAt(new Date());
        user.setCreatedAt(new Date());
        user.setAvatar(null);
        user.setRole(UserRole.USER.getRole());
        user.setStatus(UserStatus.ENABLED.getCode());
        int count = userMapper.insertOne(user);
        if (count > 0) {
            return ResponseResult.success(ResponseMessage.SUCCESS_REGISTER);
        }
        return ResponseResult.error();
    }
}
