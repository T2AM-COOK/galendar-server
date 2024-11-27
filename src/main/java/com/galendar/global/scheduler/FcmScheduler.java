package com.galendar.global.scheduler;

import com.galendar.domain.contest.dto.response.ContestDeadlineResponse;
import com.galendar.domain.contest.service.querydsl.ContestQueryService;
import com.galendar.global.firebase.service.FcmMessageService;
import com.galendar.global.firebase.service.FcmTokenService;
import com.galendar.global.firebase.service.FirebaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class FcmScheduler {

    private final ContestQueryService contestQueryService;
    private final FcmMessageService fcmMessageService;
    private final FcmTokenService fcmTokenService;
    private final FirebaseService firebaseService;

    @Scheduled(cron = "0 0 9 * * *")
    public void sendContestSubmissionReminders() {
        final LocalDate now = LocalDate.now();

        List<LocalDate> deadlineDates = List.of(
                now.plusDays(1),
                now.plusDays(3)
        );

        List<ContestDeadlineResponse> contestDeadlineResults = contestQueryService.findContestsBySubmitEndDate(deadlineDates);

        for (ContestDeadlineResponse deadlineResult : contestDeadlineResults) {
            String fcmToken = fcmTokenService.get(deadlineResult.getEmail());
            if (!StringUtils.hasText(fcmToken)) continue;
            Period period = Period.between(now, deadlineResult.getSubmitEndDate());
            String type = period.getDays() == 1 ? "D-1" : "D-3";
            Map<String, String> variables = Map.of("contestTitle", deadlineResult.getTitle());
            String title = fcmMessageService.getTitle(type, variables);
            String body = fcmMessageService.getBody(type, variables);
            firebaseService.sendMessage(fcmToken, title, body);
        }

    }
}
