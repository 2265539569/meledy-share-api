package com.cpujazz.front.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.cpujazz.common.enumeration.ResponseMessage;
import com.cpujazz.front.pojo.dto.UserLoginDto;
import com.cpujazz.front.pojo.dto.UserRegisterDto;
import com.cpujazz.front.pojo.result.ResponseResult;
import com.cpujazz.front.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseResult register(@RequestBody UserRegisterDto userRegisterDto) {
        return userService.register(userRegisterDto);
    }

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

    @GetMapping("/roomInfo")
    public ResponseResult roomInfo() {
        return userService.roomInfo();
    }
}

