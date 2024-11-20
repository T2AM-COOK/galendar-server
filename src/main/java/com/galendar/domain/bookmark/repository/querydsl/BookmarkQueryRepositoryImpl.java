package com.galendar.domain.bookmark.repository.querydsl;

import com.galendar.domain.bookmark.dto.request.BookmarkRequest;
import com.galendar.domain.bookmark.dto.response.BookmarkResponse;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.galendar.domain.bookmark.entity.QBookmarkEntity.bookmarkEntity;
import static com.galendar.domain.contest.entity.QContestEntity.contestEntity;

@Repository
@RequiredArgsConstructor
public class BookmarkQueryRepositoryImpl implements BookmarkQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<BookmarkResponse> findByUserId(BookmarkRequest request, Long userId) {
        return queryFactory
                .select(bookmarkProjection())
                .from(bookmarkEntity)
                .innerJoin(bookmarkEntity.contestEntity, contestEntity)
                .where(
                        bookmarkEntity.userEntity.id.eq(userId),
                        containTitle(request.getKeyword())
                )
                .offset((request.getPage() - 1) * request.getSize())
                .limit(request.getSize())
                .orderBy(bookmarkEntity.id.desc())
                .fetch();

    }

    private BooleanExpression containTitle(String keyword) {
        if (keyword == null || keyword.equals("")) return null;
        return contestEntity.title.contains(keyword);
    }

    private ConstructorExpression<BookmarkResponse> bookmarkProjection() {
        return Projections.constructor(
                BookmarkResponse.class,
                bookmarkEntity.id,
                contestEntity.id,
                contestEntity.title,
                contestEntity.link,
                contestEntity.imgLink,
                contestEntity.submitStartDate,
                contestEntity.submitEndDate,
                contestEntity.contestStartDate,
                contestEntity.contestEndDate
        );
    }
}
