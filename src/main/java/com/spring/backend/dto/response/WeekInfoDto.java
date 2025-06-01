package com.spring.backend.dto.response;

import java.util.Date;

public interface WeekInfoDto {
    int getWeek();
    Date getWeekStaDate();
    Date getWeekEndDate();
    String getVacationYn();
    String getMemberVacationYn();
}
