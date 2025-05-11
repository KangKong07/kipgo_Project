package com.spring.backend;

import com.spring.backend.service.WeekSchedulerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WeekSchedulerServiceTest {

    @Autowired
    private WeekSchedulerService weekSchedulerService;

    @Test
    void testGenerateWeekly() {
        weekSchedulerService.generateWeeklyForToday();
    }
}

