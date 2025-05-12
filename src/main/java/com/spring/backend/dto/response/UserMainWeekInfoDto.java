package com.spring.backend.dto.response;

import java.util.Date;


/*
 * 최근 주차 정보 DTO
 *  ** projection과 return이 동일하여 하나로 사용함
 */
public interface UserMainWeekInfoDto {

    int getWeek();
    Date getWeekStaDate();
    Date getWeekEndDate();
    String getIsCurrent();
    String getIsFeedbackReg();
}
