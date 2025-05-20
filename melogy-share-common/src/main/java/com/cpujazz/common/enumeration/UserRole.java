package com.cpujazz.common.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
    USER("USER"),
    ADMIN("ADMIN");

    private final String role;
}
