package com.galendar.domain.user.repository;

import com.galendar.domain.user.entity.UserEntity;
import com.galendar.global.security.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserAuthHolder {
    public UserEntity current() {
        return ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).userEntity();
    }
}
