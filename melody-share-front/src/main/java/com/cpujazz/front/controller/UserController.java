package com.cpujazz.front.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.cpujazz.common.enumeration.ResponseMessage;
import com.cpujazz.front.pojo.dto.UserLoginDto;
import com.cpujazz.front.pojo.result.ResponseResult;
import com.cpujazz.front.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody UserLoginDto userLoginDto) {
        return userService.login(userLoginDto);
    }

    @GetMapping("/logout")
    public ResponseResult logout() {
        StpUtil.logout();
        return ResponseResult.success(ResponseMessage.SUCCESS_LOGOUT);
    }

    @GetMapping("/info")
    public ResponseResult info() {
        return userService.info();
    }
}

