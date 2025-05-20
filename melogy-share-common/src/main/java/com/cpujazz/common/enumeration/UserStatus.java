package com.cpujazz.common.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {
    ENABLED(0),
    DISABLED(1);
    private final int code;
}
