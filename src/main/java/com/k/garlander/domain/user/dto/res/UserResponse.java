package com.k.garlander.domain.user.dto.res;


import lombok.Builder;
import lombok.Getter;
import com.k.garlander.domain.user.entity.UserEntity;

@Getter
@Builder
public class UserResponse {
    private String email;
    private String nickname;

    public static UserResponse fromUserEntity(UserEntity userEntity) {
        return UserResponse.builder()
                .email(userEntity.getEmail())
                .build();
    }
}