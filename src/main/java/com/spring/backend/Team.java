package com.spring.backend;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "TEAM")
public class Team {
    @Id
    @Column(name = "TEAM_ID")
    private String TEAM_ID;             // 팀ID

    @Column(name = "TEAM_NM")
    private String TEAM_NM;             // 팀명

    @Column(name = "WEEK_STA_DAY_CD")
    private String WEEK_STA_DAY_CD;     // 주차시작요일코드

    @Column(name = "VACATION_LIMIT")
    private int VACATION_LIMIT;         // 휴가제한횟수

    @Column(name = "PUSH_USE_YN")
    private String PUSH_USE_YN;         // 알림발송여부

    @Column(name = "GOAL_REG_DEADLINE")
    private int GOAL_REG_DEADLINE;      // 목표작성기한

    @Column(name = "FEEDBACK_REG_DEADLINE")
    private int FEEDBACK_REG_DEADLINE;  // 피드백작성기한

    @Column(name = "ADMIN_ID")
    private String ADMIN_ID;            // 팀관리자ID

    @Column(name = "STA_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date STA_DATE;              // 팀시작일

    @Column(name = "END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date END_DATE;              // 팀종료일

    @Column(name = "CHK_ID")
    private String CHK_ID;              // 최종작업자

    @Column(name = "CHK_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date CHK_DATE;              // 최종작업시간

    public String getTEAM_ID() {
        return TEAM_ID;
    }

    public void setTEAM_ID(String TEAM_ID) {
        this.TEAM_ID = TEAM_ID;
    }

    public String getTEAM_NM() {
        return TEAM_NM;
    }

    public void setTEAM_NM(String TEAM_NM) {
        this.TEAM_NM = TEAM_NM;
    }

    public String getWEEK_STA_DAY_CD() {
        return WEEK_STA_DAY_CD;
    }

    public void setWEEK_STA_DAY_CD(String WEEK_STA_DAY_CD) {
        this.WEEK_STA_DAY_CD = WEEK_STA_DAY_CD;
    }

    public int getVACATION_LIMIT() {
        return VACATION_LIMIT;
    }

    public void setVACATION_LIMIT(int VACATION_LIMIT) {
        this.VACATION_LIMIT = VACATION_LIMIT;
    }

    public String getPUSH_USE_YN() {
        return PUSH_USE_YN;
    }

    public void setPUSH_USE_YN(String PUSH_USE_YN) {
        this.PUSH_USE_YN = PUSH_USE_YN;
    }

    public int getGOAL_REG_DEADLINE() {
        return GOAL_REG_DEADLINE;
    }

    public void setGOAL_REG_DEADLINE(int GOAL_REG_DEADLINE) {
        this.GOAL_REG_DEADLINE = GOAL_REG_DEADLINE;
    }

    public int getFEEDBACK_REG_DEADLINE() {
        return FEEDBACK_REG_DEADLINE;
    }

    public void setFEEDBACK_REG_DEADLINE(int FEEDBACK_REG_DEADLINE) {
        this.FEEDBACK_REG_DEADLINE = FEEDBACK_REG_DEADLINE;
    }

    public String getADMIN_ID() {
        return ADMIN_ID;
    }

    public void setADMIN_ID(String ADMIN_ID) {
        this.ADMIN_ID = ADMIN_ID;
    }

    public Date getSTA_DATE() {
        return STA_DATE;
    }

    public void setSTA_DATE(Date STA_DATE) {
        this.STA_DATE = STA_DATE;
    }

    public Date getEND_DATE() {
        return END_DATE;
    }

    public void setEND_DATE(Date END_DATE) {
        this.END_DATE = END_DATE;
    }

    public String getCHK_ID() {
        return CHK_ID;
    }

    public void setCHK_ID(String CHK_ID) {
        this.CHK_ID = CHK_ID;
    }

    public Date getCHK_DATE() {
        return CHK_DATE;
    }

    public void setCHK_DATE(Date CHK_DATE) {
        this.CHK_DATE = CHK_DATE;
    }

    @Override
    public String toString() {
        return "Team{" +
                "TEAM_ID='" + TEAM_ID + '\'' +
                ", TEAM_NM='" + TEAM_NM + '\'' +
                ", WEEK_STA_DAY_CD='" + WEEK_STA_DAY_CD + '\'' +
                ", VACATION_LIMIT=" + VACATION_LIMIT +
                ", PUSH_USE_YN='" + PUSH_USE_YN + '\'' +
                ", GOAL_REG_DEADLINE=" + GOAL_REG_DEADLINE +
                ", FEEDBACK_REG_DEADLINE=" + FEEDBACK_REG_DEADLINE +
                ", ADMIN_ID='" + ADMIN_ID + '\'' +
                ", STA_DATE=" + STA_DATE +
                ", END_DATE=" + END_DATE +
                ", CHK_ID='" + CHK_ID + '\'' +
                ", CHK_DATE=" + CHK_DATE +
                '}';
    }
}
