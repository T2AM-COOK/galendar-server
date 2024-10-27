package com.k.garlander.domain.bookmark.entity;

import com.k.garlander.domain.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "bookmark")
public class BookmarkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_id", nullable = false)
    private Long bookmark_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    // 대회 엔티티 수정하기
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "contest_id", nullable = false)
//    private ContestEntity post;

//    @Builder
//    public BookmarkEntity(UserEntity user, ContestEntity contest) {
//        this.user = user;
//        this.contest = contest;
//    }
}
