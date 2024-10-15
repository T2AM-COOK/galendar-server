package com.galendar.domain.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {
//    @Email
    @NotBlank(message = "이메일을 입력해 주세요.")
    private String email;

    @NotBlank(message = "비밀번호를 입력해 주세요.")
    @Size(min = 4, message = "최소 4자 이상으로 입력해 주세요.")
    private String password;

    @NotBlank(message = "이름을 입력해 주세요.")
    private String name;

}
