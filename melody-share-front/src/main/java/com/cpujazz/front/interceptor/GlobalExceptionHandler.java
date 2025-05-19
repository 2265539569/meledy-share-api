package com.cpujazz.front.interceptor;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.util.SaResult;
import com.cpujazz.common.enumeration.ResponseMessage;
import com.cpujazz.front.pojo.result.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    // 全局异常拦截 
    @ExceptionHandler
    public SaResult handlerException(Exception e) {
        log.info(e.getMessage());
        return SaResult.error(e.getMessage());
    }

    @ExceptionHandler(NotLoginException.class)
    public ResponseResult handlerException(NotLoginException e) {
        log.info(e.getMessage());
        return switch (e.getType()) {
            case NotLoginException.NOT_TOKEN -> ResponseResult.error(ResponseMessage.TOKEN_UNKNOWN);
            case NotLoginException.INVALID_TOKEN -> ResponseResult.error(ResponseMessage.TOKEN_INVALID);
            case NotLoginException.TOKEN_TIMEOUT -> ResponseResult.error(ResponseMessage.TOKEN_TIMEOUT);
            default -> ResponseResult.error(ResponseMessage.ERROR);
        };
    }
}

