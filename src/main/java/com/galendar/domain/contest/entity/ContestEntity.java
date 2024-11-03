package com.galendar.domain.contest.entity;

import com.galendar.domain.contest.entity.enums.ContestCost;
import com.galendar.domain.user.entity.UserEntity;
import com.galendar.global.common.entity.BaseTimeEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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