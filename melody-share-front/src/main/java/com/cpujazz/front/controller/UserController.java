package com.cpujazz.front.controller;



import com.cpujazz.front.pojo.entity.User;
import com.cpujazz.front.pojo.result.ResponseResult;
import com.cpujazz.front.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/user")
@AllArgsConstructor
@ResponseBody
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseResult userRegister(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public ResponseResult userLogin(@RequestBody User user){
        return userService.login(user);
    }
}

