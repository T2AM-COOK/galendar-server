package com.galendar.domain.contest.entity;

import com.galendar.domain.target.entity.TargetEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_contest_target")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class ContestTargetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contest_id")
    private ContestEntity contestEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_id")
    private TargetEntity targetEntity;

}
