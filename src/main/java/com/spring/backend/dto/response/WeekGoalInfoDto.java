package com.spring.backend.dto.response;

import java.util.Date;

public interface WeekGoalInfoDto {

    // ====== WEEK_MEMBER ======
    String getTeamId();
    Integer getWeek();
    String getMemberId();
    String getVacationYn();
    String getGoalRegYn();
    String getFeedbackRegYn();
    Double getTotAchieveRate();
    String getComment();
    String getMainGoalUnmet();
//    String getMemberChkId();     // alias 필요
//    Date getMemberChkDate();

    // ====== WEEK_GOAL ======
    Integer getGoalNo();
    String getGoal();
    Integer getOrderSeq();
    String getMainGoalYn();
    String getAchieveStatusCd();
    String getFeedback();
    String getGoalChkId();       // alias 필요
    Date getGoalChkDate();

}
