package com.k.garlander.domain.user.dto.req;

import lombok.Getter;

@Getter
public class RegisterRequest {
    private String email;
    private String password;
}
