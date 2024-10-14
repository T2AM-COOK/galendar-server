package com.galendar.domain.user.mapper;

import com.galendar.domain.user.dto.User;
import com.galendar.domain.user.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toDTO(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getId())
                .password(userEntity.getPassword())
                .email(userEntity.getEmail())
                .name(userEntity.getName())
                .role(userEntity.getRole())
                .build();
    }

}
