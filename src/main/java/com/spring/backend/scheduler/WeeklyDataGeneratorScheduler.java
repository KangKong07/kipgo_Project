package com.spring.backend.scheduler;

import com.spring.backend.service.WeekSchedulerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class WeeklyDataGeneratorScheduler {

    private final WeekSchedulerService weekSchedulerService;

    /**
     * 주차 생성 스케줄러
     *  - 매일 오전 5시에 실행
     */
    @Scheduled(cron = "0 0 5 * * *", zone = "Asia/Seoul")
    @Profile("prod") // prod 프로필에서만 작동
    public void generateWeeklyData() {
        log.info("📅 [Scheduler] 주차 데이터 생성 시작!");

        try {
            weekSchedulerService.generateWeeklyForToday();
            log.info("✅ [Scheduler] 주차 데이터 생성 완료");
        } catch (Exception e) {
            log.error("❌ [Scheduler] 주차 데이터 생성 실패", e);
        }
    }
}
