package com.spring.backend.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "WEEK_MEMBER")
public class Week_Member {

    @Id @Column(name = "TEAM_ID")
    private String TEAM_ID;     // 팀ID

    @Id @Column(name = "WEEK")
    private int WEEK;           // 주차

    @Id @Column(name = "MEMBER_ID")
    private String MEMBER_ID;   // 회원ID

    @Column(name = "VACATION_YN")
    private String VACATION_YN;     // 휴가여부

    @Column(name = "GOAL_REG_YN")
    private String GOAL_REG_YN;     // 목표작성여부

    @Column(name = "FEEDBACK_REG_YN")
    private String FEEDBACK_REG_YN; // 피드백작성여부

    @Column(name = "TOT_ACHIEVE_RATE")
    private int TOT_ACHIEVE_RATE;   // 총달성률

    @Column(name = "COMMENT")
    private String COMMENT;         // 총평

    @Column(name = "MAIN_GOAL_UNMET")
    private String MAIN_GOAL_UNMET; // 메인목표 미달성여부

    @Column(name = "CHK_ID")
    private String CHK_ID;          // 최종작업자

    @Column(name = "CHK_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date CHK_DATE;          // 최종작업시간

    public String getTEAM_ID() {
        return TEAM_ID;
    }

    public void setTEAM_ID(String TEAM_ID) {
        this.TEAM_ID = TEAM_ID;
    }

    public int getWEEK() {
        return WEEK;
    }

    public void setWEEK(int WEEK) {
        this.WEEK = WEEK;
    }

    public String getMEMBER_ID() {
        return MEMBER_ID;
    }

    public void setMEMBER_ID(String MEMBER_ID) {
        this.MEMBER_ID = MEMBER_ID;
    }

    public String getVACATION_YN() {
        return VACATION_YN;
    }

    public void setVACATION_YN(String VACATION_YN) {
        this.VACATION_YN = VACATION_YN;
    }

    public String getGOAL_REG_YN() {
        return GOAL_REG_YN;
    }

    public void setGOAL_REG_YN(String GOAL_REG_YN) {
        this.GOAL_REG_YN = GOAL_REG_YN;
    }

    public String getFEEDBACK_REG_YN() {
        return FEEDBACK_REG_YN;
    }

    public void setFEEDBACK_REG_YN(String FEEDBACK_REG_YN) {
        this.FEEDBACK_REG_YN = FEEDBACK_REG_YN;
    }

    public int getTOT_ACHIEVE_RATE() {
        return TOT_ACHIEVE_RATE;
    }

    public void setTOT_ACHIEVE_RATE(int TOT_ACHIEVE_RATE) {
        this.TOT_ACHIEVE_RATE = TOT_ACHIEVE_RATE;
    }

    public String getCOMMENT() {
        return COMMENT;
    }

    public void setCOMMENT(String COMMENT) {
        this.COMMENT = COMMENT;
    }

    public String getMAIN_GOAL_UNMET() {
        return MAIN_GOAL_UNMET;
    }

    public void setMAIN_GOAL_UNMET(String MAIN_GOAL_UNMET) {
        this.MAIN_GOAL_UNMET = MAIN_GOAL_UNMET;
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
        return "Week_Member{" +
                "TEAM_ID='" + TEAM_ID + '\'' +
                ", WEEK=" + WEEK +
                ", MEMBER_ID='" + MEMBER_ID + '\'' +
                ", VACATION_YN='" + VACATION_YN + '\'' +
                ", GOAL_REG_YN='" + GOAL_REG_YN + '\'' +
                ", FEEDBACK_REG_YN='" + FEEDBACK_REG_YN + '\'' +
                ", TOT_ACHIEVE_RATE=" + TOT_ACHIEVE_RATE +
                ", COMMENT='" + COMMENT + '\'' +
                ", MAIN_GOAL_UNMET='" + MAIN_GOAL_UNMET + '\'' +
                ", CHK_ID='" + CHK_ID + '\'' +
                ", CHK_DATE=" + CHK_DATE +
                '}';
    }
}
