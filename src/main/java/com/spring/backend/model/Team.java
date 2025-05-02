package com.spring.backend.model;

import jakarta.persistence.*;

import java.util.Date;

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

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamNm() {
        return teamNm;
    }

    public void setTeamNm(String teamNm) {
        this.teamNm = teamNm;
    }

    public String getWeekStaDayCd() {
        return weekStaDayCd;
    }

    public void setWeekStaDayCd(String weekStaDayCd) {
        this.weekStaDayCd = weekStaDayCd;
    }

    public int getVacationLimit() {
        return vacationLimit;
    }

    public void setVacationLimit(int vacationLimit) {
        this.vacationLimit = vacationLimit;
    }

    public String getPushUseYn() {
        return pushUseYn;
    }

    public void setPushUseYn(String pushUseYn) {
        this.pushUseYn = pushUseYn;
    }

    public int getGoalRegDeadline() {
        return goalRegDeadline;
    }

    public void setGoalRegDeadline(int goalRegDeadline) {
        this.goalRegDeadline = goalRegDeadline;
    }

    public int getFeedbackRegDeadline() {
        return feedbackRegDeadline;
    }

    public void setFeedbackRegDeadline(int feedbackRegDeadline) {
        this.feedbackRegDeadline = feedbackRegDeadline;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public Date getStaDate() {
        return staDate;
    }

    public void setStaDate(Date staDate) {
        this.staDate = staDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getChkId() {
        return chkId;
    }

    public void setChkId(String chkId) {
        this.chkId = chkId;
    }

    public Date getChkDate() {
        return chkDate;
    }

    public void setChkDate(Date chkDate) {
        this.chkDate = chkDate;
    }


    @Override
    public String toString() {
        return "Team {" +
                "teamId='" + teamId + '\'' +
                ", teamNm='" + teamNm + '\'' +
                ", weekStaDayCd='" + weekStaDayCd + '\'' +
                ", vacationLimit=" + vacationLimit +
                ", pushUseYn='" + pushUseYn + '\'' +
                ", goalRegDeadline=" + goalRegDeadline +
                ", feedbackRegDeadline=" + feedbackRegDeadline +
                ", adminId='" + adminId + '\'' +
                ", staDate=" + staDate +
                ", endDate=" + endDate +
                ", chkId='" + chkId + '\'' +
                ", chkDate=" + chkDate +
                " }";
    }
}
