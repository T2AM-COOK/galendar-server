package com.galendar.domain.contest.entity

import com.galendar.domain.user.entity.UserEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne

@Entity
class ContestLikeEntity(
    @Id
    @ManyToOne
    val contest: ContestEntity,

    @Id
    @ManyToOne
    val user: UserEntity,
)