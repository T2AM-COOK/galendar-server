package com.k.garlander.config;

import com.k.garlander.dto.CustomUserDetails;
import com.k.garlander.entity.UserEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserAuthHolder {
    public UserEntity current() {
        return ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserEntity();
    }
}
