package com.cpujazz.front.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserStatus {
    DISABLED(0),
    ENABLED(1);
    private final int code;
}
