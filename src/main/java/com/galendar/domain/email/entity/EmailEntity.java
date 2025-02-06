package com.galendar.domain.email.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_email")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class EmailEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    private String verificationCode;

    private LocalDateTime expirationDate;

    @Column(nullable = false)
    private boolean isVerified;

    public void verify(){
        this.isVerified = true;
        this.verificationCode = null;
        this.expirationDate = null;
    }

}