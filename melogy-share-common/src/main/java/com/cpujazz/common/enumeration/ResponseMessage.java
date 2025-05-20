package com.cpujazz.common.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseMessage {
    SUCCESS(200,"操作成功"),
    SUCCESS_LOGIN(200, "登录成功"),
    SUCCESS_LOGOUT(200, "退出登录成功"),
    SUCCESS_REGISTER(200, "注册成功"),
    SUCCESS_ROOM_INSERT(200, "房间添加成功"),
    SUCCESS_ROOM_USER_JOIN(200, "用户加入房间成功"),
    SUCCESS_ROOM_USER_EXIT(200, "用户离开房间成功"),
    TOKEN_INVALID(403, "无效token"),
    TOKEN_UNKNOWN(403, "未知token"),
    TOKEN_TIMEOUT(403, "token已过期"),
    ERROR(501, "操作失败"),
    ERROR_USER_EXISTS(502, "账户已存在"),
    ERROR_USER_NOT_EXISTS(503, "账户不存在"),
    ERROR_USER_LOGIN_FAILED(504, "登录失败, 请检查你的账号密码是否正确"),
    ERROR_ROOM_NAME_EXISTS(505, "房间名已存在"),
    ERROR_ROOM_NAME_NOT_EXISTS(506, "房间名不存在"),
    ERROR_ROOM_PASSWORD(507, "房间密码错误"),
    ERROR_ROOM_USER_EXISTS(508, "用户已经加入房间了"),
    ERROR_USER_ALREADY_IN_ROOM(509, "用户已经加入其他房间了，请勿重复加入"),
    ERROR_ROOM_NOT_EXISTS(510, "房间不存在"),
    ;
    private final int code;
    private final String message;
}
