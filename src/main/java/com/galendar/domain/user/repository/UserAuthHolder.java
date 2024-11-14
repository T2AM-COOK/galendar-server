package com.galendar.domain.user.repository;

import com.galendar.domain.user.entity.UserEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserAuthHolder {
    public UserEntity current() {
        return (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}