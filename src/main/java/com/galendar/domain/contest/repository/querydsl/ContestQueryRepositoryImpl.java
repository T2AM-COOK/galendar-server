package com.galendar.domain.contest.repository.querydsl;

import com.galendar.domain.contest.dto.request.ContestRequest;
import com.galendar.domain.contest.dto.response.ContestDetailResponse;
import com.galendar.domain.contest.dto.response.ContestResponse;
import com.galendar.domain.region.dto.RegionDTO;
import com.galendar.domain.target.dto.TargetDTO;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.galendar.domain.bookmark.entity.QBookmarkEntity.bookmarkEntity;
import static com.galendar.domain.contest.entity.QContestEntity.contestEntity;
import static com.galendar.domain.contest.entity.QContestRegionEntity.contestRegionEntity;
import static com.galendar.domain.contest.entity.QContestTargetEntity.contestTargetEntity;
import static com.galendar.domain.region.entity.QRegionEntity.regionEntity;
import static com.galendar.domain.target.entity.QTargetEntity.targetEntity;
import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.set;

@Repository
@RequiredArgsConstructor
public class ContestQueryRepositoryImpl implements ContestQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ContestResponse> findWithBookmark(ContestRequest request, Long userId){
        return queryFactory
                .select(contestProjection())
                .from(contestEntity)
                .innerJoin(contestEntity.contestTargets, contestTargetEntity)
                .innerJoin(contestEntity.contestRegions, contestRegionEntity)
                .leftJoin(bookmarkEntity).on(bookmarkEntity.contestEntity.eq(contestEntity).and(bookmarkEntity.userEntity.id.eq(userId)))
                .where(
                        containKeyword(request.getKeyword()),
                        eqTargets(request.getTargets()),
                        eqRegions(request.getRegions()),
                        afterSubmitStartDate(request.getSubmitStartDate()),
                        beforeSubmitEndDate(request.getSubmitEndDate())
                )
                .offset((request.getPage() - 1) * request.getSize())
                .limit(request.getSize())
                .orderBy(contestEntity.id.desc())
                .groupBy(contestEntity.id)
                .fetch();
    }

    @Override
    public Optional<ContestDetailResponse> findByIdWithBookmark(Long id, Long userId) {

        return queryFactory
                .selectFrom(contestEntity)
                .innerJoin(contestEntity.contestTargets, contestTargetEntity)
                .innerJoin(contestEntity.contestRegions, contestRegionEntity)
                .leftJoin(bookmarkEntity).on(bookmarkEntity.contestEntity.eq(contestEntity).and(bookmarkEntity.userEntity.id.eq(userId)))
                .where(
                        eqId(id)
                )
                .transform(
                        groupBy(contestEntity.id).list(
                                Projections.constructor(
                                        ContestDetailResponse.class,
                                        contestEntity.id,
                                        contestEntity.title,
                                        contestEntity.content,
                                        contestEntity.cost,
                                        contestEntity.link,
                                        contestEntity.imgLink,
                                        contestEntity.submitStartDate,
                                        contestEntity.submitEndDate,
                                        contestEntity.contestStartDate,
                                        contestEntity.contestEndDate,
                                        bookmarkEntity.isNotNull(),
                                        set(
                                                Projections.constructor(
                                                        TargetDTO.class,
                                                        targetEntity.id,
                                                        targetEntity.name
                                                )
                                        ),
                                        set(
                                                Projections.constructor(
                                                        RegionDTO.class,
                                                        regionEntity.id,
                                                        regionEntity.name
                                                )
                                        )
                                )
                        )
                ).stream().findFirst();
    }

    private BooleanExpression eqId(Long id) {
        if (id == null) return null;
        return contestEntity.id.eq(id);
    }

    private BooleanExpression beforeSubmitEndDate(LocalDate submitEndDate) {
        if (submitEndDate == null) return null;
        return contestEntity.submitEndDate.loe(submitEndDate);
    }

    private BooleanExpression afterSubmitStartDate(LocalDate submitStartDate) {
        if (submitStartDate == null) return null;
        return contestEntity.submitStartDate.goe(submitStartDate);
    }

    private BooleanExpression eqRegions(List<Long> regions) {
        if (regions == null) return null;
        return contestRegionEntity.regionEntity.id.in(regions);
    }

    private BooleanExpression eqTargets(List<Long> targets) {
        if (targets == null) return null;
        return contestTargetEntity.targetEntity.id.in(targets);
    }

    private BooleanExpression containKeyword(String keyword) {
        if (keyword == null || keyword.equals("")) return null;
        return containTitle(keyword).or(containContent(keyword));
    }

    private BooleanExpression containTitle(String keyword) {
        if (keyword == null) return null;
        return contestEntity.title.contains(keyword);
    }

    private BooleanExpression containContent(String keyword) {
        if (keyword == null) return null;
        return contestEntity.content.contains(keyword);
    }


    private ConstructorExpression<ContestResponse> contestProjection() {
        return Projections.constructor(
                ContestResponse.class,
                contestEntity.id,
                contestEntity.title,
                contestEntity.content,
                contestEntity.cost,
                contestEntity.link,
                contestEntity.imgLink,
                bookmarkEntity.isNotNull(),
                contestEntity.submitStartDate,
                contestEntity.submitEndDate,
                contestEntity.contestStartDate,
                contestEntity.contestEndDate
        );
    }

}
