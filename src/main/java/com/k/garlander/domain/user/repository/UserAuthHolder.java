package com.k.garlander.domain.user.repository;

import com.k.garlander.domain.user.dto.res.CustomUserDetails;
import com.k.garlander.domain.user.entity.UserEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserAuthHolder {
    public UserEntity current() {
        return ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
