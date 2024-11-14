package com.galendar.global.security;

import com.galendar.domain.user.entity.UserEntity;
import com.galendar.domain.user.exception.UserNotFoundException;
import com.galendar.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class UserSecurity {
    private final UserRepository userRepository;

    public CustomUserDetails getUser() {
        return (CustomUserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    public UserEntity getUserEntity() {
        return userRepository.findByEmail(getUser().getUsername()).orElseThrow(UserNotFoundException::new);
    }
}
