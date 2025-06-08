package com.spring.backend;

import com.spring.backend.service.WeekSchedulerService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled("[주의] 매일 오전 5시에 실행되는 스케줄러를 테스트하는 것이므로, 테스트 시에만 사용하세요")
@SpringBootTest
public class WeekSchedulerServiceTest {

    @Autowired
    private WeekSchedulerService weekSchedulerService;

    @Test
    void testGenerateWeekly() {
        weekSchedulerService.generateWeeklyForToday();
    }
}

