package com.k.garlander.dto;

import com.k.garlander.entity.UserEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Optional;

@ApiModel
@Getter
@NoArgsConstructor
public class UserDTO {
    private String id;
    @ApiModelProperty(value = "사용자 이메일", example = "example@example.com")
    private String email;
    @ApiModelProperty(value = "사용자 비밀번호", example = "1234")
    private String password;

    @Builder
    public UserDTO(String id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public static UserDTO of(Optional<UserEntity> userEntity) {
        return UserDTO.builder()
                .email(userEntity.get().getEmail())
                .build();
    }
}
