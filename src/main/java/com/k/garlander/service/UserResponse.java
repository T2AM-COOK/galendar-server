package com.k.garlander.service;

import lombok.Builder;
import lombok.Getter;
import com.k.garlander.entity.UserEntity;

@Getter
@Builder
public class UserResponse {
    private String email;

    public static UserResponse fromUserEntity(UserEntity userEntity) {
        return UserResponse.builder()
                .email(userEntity.getEmail())
                .build();
    }
}
