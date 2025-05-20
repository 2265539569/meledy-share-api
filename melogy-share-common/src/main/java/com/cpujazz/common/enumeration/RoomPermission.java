package com.cpujazz.common.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoomPermission {
    PUBLIC(0),
    PRIVATE(1);

    private final int code;
}
