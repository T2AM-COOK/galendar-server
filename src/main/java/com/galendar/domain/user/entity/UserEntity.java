package com.galendar.domain.user.entity;

import com.galendar.domain.user.entity.enums.UserRole;
import com.galendar.global.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity(name = "tb_user")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class UserEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserRole role;
}