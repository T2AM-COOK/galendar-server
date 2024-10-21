package com.galendar.domain.user.entity.enums;

import lombok.Getter;

@Getter
public enum UserRole {
    USER("ROLE_USER"),
    MANAGER("ROLE_MANAGER"),
    ADMIN("ROLE_ADMIN");

    private final String key;

    UserRole(String key) {
        this.key = key;
    }
}