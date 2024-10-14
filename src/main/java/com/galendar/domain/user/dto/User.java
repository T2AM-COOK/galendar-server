package com.galendar.domain.user.dto;

import com.galendar.domain.user.entity.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    private Long id;

    private String email;

    private String password;

    private String name;

    private UserRole role;

}
