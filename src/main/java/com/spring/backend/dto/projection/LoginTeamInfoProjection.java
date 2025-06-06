package com.spring.backend.dto.projection;

public interface LoginTeamInfoProjection {
    // [ TEAM ]
    String getTeamNm();
    Integer getVacationLimit();
    Integer getGoalRegDeadline();
    Integer getFeedbackRegDeadline();

    // [ TEAM_MEMBER ]
    String getNickname();
    String getTeamSetYn();
    Integer getMemberVacationLimit();
}
