package com.k.garlander.domain.bookmark.entity;

import com.k.garlander.domain.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name = "bookmark", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "contest_id"})
})
public class BookmarkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_id", nullable = false)
    private Long bookmark_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "contest_id", nullable = false)
//    private ContestEntity contest;

    @CreatedDate
    private LocalDate createdAt;
}
