package com.garlander.domain.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JoinDto {
    private String userName;
    private String passWord;
}