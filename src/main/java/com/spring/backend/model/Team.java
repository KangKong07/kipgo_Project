package com.spring.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "TEAM")
public class Team {

    @Id
    @Column(name = "TEAM_ID")
    private String teamId;             // 팀ID

    @Column(name = "TEAM_NM")
    private String teamNm;             // 팀명

    @Column(name = "WEEK_STA_DAY_CD")
    private String weekStaDayCd;     // 주차시작요일코드

    @Column(name = "VACATION_LIMIT")
    private int vacationLimit;         // 휴가제한횟수

    @Column(name = "PUSH_USE_YN")
    private String pushUseYn;         // 알림발송여부

    @Column(name = "GOAL_REG_DEADLINE")
    private int goalRegDeadline;      // 목표작성기한

    @Column(name = "FEEDBACK_REG_DEADLINE")
    private int feedbackRegDeadline;  // 피드백작성기한

    @Column(name = "ADMIN_ID")
    private String adminId;            // 팀관리자ID

    @Column(name = "STA_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date staDate;              // 팀시작일

    @Column(name = "END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;              // 팀종료일

    @Column(name = "CHK_ID")
    private String chkId;              // 최종작업자

    @Column(name = "CHK_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date chkDate;              // 최종작업시간

    /* [setter] */
    public void setTeamNm(String teamNm) {
        this.teamNm = teamNm;
    }
    public void setWeekStaDayCd(String weekStaDayCd) {
        this.weekStaDayCd = weekStaDayCd;
    }
    public void setVacationLimit(int vacationLimit) {
        this.vacationLimit = vacationLimit;
    }
    public void setPushUseYn(String pushUseYn) {
        this.pushUseYn = pushUseYn;
    }
    public void setGoalRegDeadline(int goalRegDeadline) {
        this.goalRegDeadline = goalRegDeadline;
    }
    public void setFeedbackRegDeadline(int feedbackRegDeadline) {
        this.feedbackRegDeadline = feedbackRegDeadline;
    }
    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
    public void setStaDate(Date staDate) {
        this.staDate = staDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public void setChkDate(Date chkDate) {
        this.chkDate = chkDate;
    }
    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }
}
