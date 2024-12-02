package com.galendar.domain.user.dto;

import com.galendar.domain.user.entity.enums.UserRole;
import lombok.*;

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
