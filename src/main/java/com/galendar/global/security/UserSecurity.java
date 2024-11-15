package com.galendar.global.security;

import com.galendar.domain.user.dto.User;
import com.galendar.domain.user.entity.UserEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
public class UserSecurity {

    public User getUser() {
        return ((CustomUserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getUser();
    }

    public UserEntity getUserEntity() {
        return UserEntity.builder()
                .id(getUser().getId())
                .build();
    }

}