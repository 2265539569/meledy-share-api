package com.cpujazz.common.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoomStatus {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");

    private final String status;
}
