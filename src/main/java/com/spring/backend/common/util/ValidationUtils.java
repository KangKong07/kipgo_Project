package com.spring.backend.common.util;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

@Component
public class ValidationUtils {
    // 휴가일수 및 등록 기한 검증 로직
    public void validateGoalDeadline(Date startDate, int deadlineDays) {
        LocalDate deadline = startDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
                .plusDays(deadlineDays);

        Date deadlineDate = Date.from(deadline.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date now = new Date();

        // 날짜 포맷 적용
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM월 dd일까지");
        String formattedDeadline = formatter.format(deadlineDate);

        if (now.after(deadlineDate)) {
            throw new IllegalArgumentException("등록 기한이 지났습니다. (기한: " + deadlineDate + "까지)");
        }
    }
}
