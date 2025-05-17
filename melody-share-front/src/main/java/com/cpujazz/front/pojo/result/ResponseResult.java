package com.cpujazz.front.pojo.result;

import com.cpujazz.common.enumeration.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;


@Data
@AllArgsConstructor
public class ResponseResult {
    private int code;
    private String message;
    private Object data;

    @Override
    public String toString() {
        Map<String,Object> map = new HashMap<>();
        map.put("code",code);
        map.put("message",message);
        map.put("data",data);
        return map.toString();
    }

    // 无参成功返回
    public static ResponseResult success() {
        return new ResponseResult(
                ResponseMessage.SUCCESS.getCode(),
                ResponseMessage.SUCCESS.getMessage(),
                null);
    }

    // 有参自定义成功返回
    public static ResponseResult success(ResponseMessage message, Object data) {
        return new ResponseResult(
                message.getCode(),
                message.getMessage(),
                data);
    }

    // 无参自定义成功返回
    public static ResponseResult success(ResponseMessage message) {
        return new ResponseResult(
                message.getCode(),
                message.getMessage(),
                null);
    }

    // 有参成功返回
    public static ResponseResult success(Object data) {
        return new ResponseResult(
                ResponseMessage.SUCCESS.getCode(),
                ResponseMessage.SUCCESS.getMessage(),
                data);
    }

    // 无参错误返回
    public static ResponseResult error() {
        return new ResponseResult(
                ResponseMessage.ERROR.getCode(),
                ResponseMessage.ERROR.getMessage(),
                null);
    }

    // 无参自定义消息错误返回
    public static ResponseResult error(ResponseMessage errorMessage) {
        return new ResponseResult(errorMessage.getCode(), errorMessage.getMessage(), null);
    }
}
