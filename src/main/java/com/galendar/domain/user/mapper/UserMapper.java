package com.galendar.domain.user.mapper;

import com.galendar.domain.user.dto.User;
import com.galendar.domain.user.dto.response.UserProfileResponse;
import com.galendar.domain.user.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toEntity(final Long id) {
        return UserEntity.builder().id(id).build();
    }

    public User toDTO(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getId())
                .password(userEntity.getPassword())
                .email(userEntity.getEmail())
                .name(userEntity.getName())
                .role(userEntity.getRole())
                .build();
    }

    public UserProfileResponse toProfileResponse(final User user) {
        return UserProfileResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }
}