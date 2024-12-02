package com.galendar.domain.contest.entity;

import com.galendar.domain.contest.entity.enums.ContestCost;
import com.galendar.domain.user.entity.UserEntity;
import com.galendar.global.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_contest")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class ContestEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;


    @OneToMany(mappedBy = "contestEntity", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ContestTargetEntity> contestTargets = new ArrayList<>();

    @OneToMany(mappedBy = "contestEntity", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ContestRegionEntity> contestRegions = new ArrayList<>();

    private String strNo;

    @NotNull
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    private ContestCost cost;

    @NotNull
    private String link;

    private String imgLink;

    private LocalDate submitStartDate;

    private LocalDate submitEndDate;

    private LocalDate contestStartDate;

    private LocalDate contestEndDate;

}