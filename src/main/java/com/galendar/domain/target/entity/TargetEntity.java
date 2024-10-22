package com.galendar.domain.target.entity;

import com.galendar.domain.contest.entity.enums.ContestCost;
import com.galendar.domain.target.entity.enums.TargetRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="tbl_target")
@Getter
@Setter
public class TargetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "target_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    // EnumType.STRING으로 선언하여 enum 값을 문자열로 데이터베이스에 저장
    private TargetRole Role;

    private Integer del;
}
