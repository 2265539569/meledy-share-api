package com.cpujazz.common.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoomPermission {
    PRIVATE(0),
    PUBLIC(1);

    private final int code;
}
