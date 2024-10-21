package com.galendar.domain.user.entity;

import com.galendar.domain.user.entity.enums.UserRole;
import com.galendar.global.common.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "tb_user") // 이 클래스가 'tb_user'라는 데이터베이스 테이블과 매핑됨을 나타냄
@Builder // 빌더 패턴을 사용하여 객체를 생성할 수 있게 해줌
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자를 생성하며, PROTECTED 접근 수준을 설정해 외부에서 직접 호출을 제한함
@AllArgsConstructor // 모든 필드를 인자로 받는 생성자를 자동으로 생성함
@Getter // 모든 필드에 대한 getter 메서드를 자동 생성
public class UserEntity extends BaseTimeEntity { // 이 클래스는 BaseTimeEntity를 상속받아 생성/수정 시간 관리
    @Id // 기본 키(Primary Key)로 사용됨을 선언
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 기본 키를 데이터베이스에서 자동 생성하도록 설정 (AUTO_INCREMENT)
    private Long id;

    @Column(unique = true, nullable = false)
    // 'email' 필드는 고유(unique)해야 하며, null 값을 허용하지 않음
    private String email;

    @NotNull
    // 'password' 필드는 null 값을 허용하지 않음
    private String password;

    @NotNull
    // 'name' 필드는 null 값을 허용하지 않음
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    // 'role' 필드는 UserRole 열거형(enum)이며, 데이터베이스에 문자열로 저장됨
    private UserRole role;
}