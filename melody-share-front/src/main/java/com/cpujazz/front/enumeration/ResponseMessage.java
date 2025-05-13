package com.cpujazz.front.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseMessage {
    SUCCESS(200,"操作成功"),
    ERROR(500, "操作失败"),
    ERROR_USER_EXISTS(501, "账户已存在"),
    ERROR_USER_LOGIN_FAILED(501, "登录失败, 请检查你的账号密码是否正确");

    private final int code;
    private final String message;
}
