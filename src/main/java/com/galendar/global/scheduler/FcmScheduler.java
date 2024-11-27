package com.galendar.global.scheduler;

import com.galendar.domain.contest.entity.ContestEntity;
import com.galendar.domain.contest.repository.querydsl.ContestQueryRepositoryImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FcmScheduler {
    private final ContestQueryRepositoryImpl contestQueryRepositoryImpl;

    @Transactional
    @Scheduled(cron = "0 0 9 * * *")
    public void sendContestSubmissionReminders() {

        LocalDate now = LocalDate.now();
        List<LocalDate> deadlineDates = List.of(
                now.plusDays(1),
                now.plusDays(3)
        );

        List<ContestEntity> contests = contestQueryRepositoryImpl.findContestsBySubmitEndDates(deadlineDates);

        for (ContestEntity contest : contests) {
            long days = ChronoUnit.DAYS.between(now, contest.getSubmitEndDate());

            String title = "";
            String body = "";

            if (days == 3) {
                title = String.format(
                        "📢마감 3일 점! 지금 바로 지원하세요!"
                );
                body = String.format(
                        "시간이 얼마 남지 않았습니다! [%s] 접수는 3일 후에 마감됩니다!",
                        contest.getTitle()
                );
            } else if (days == 1) {
                title = String.format(
                        "%s 🚨놓치면 끝! 접수 마감 D-DAY"
                );
                body = String.format(
                        "모든 것이 준비 되었습니다. 이제 당신의 도전만 남았습니. [%s] 접수가 오늘까지 진행됩니다. 힘내세요!",
                        contest.getTitle()
                );
            }
        }
    }
}
