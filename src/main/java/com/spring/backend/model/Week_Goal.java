package com.spring.backend.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "WEEK_GOAL")
public class Week_Goal {

    @Id @Column(name = "TEAM_ID")
    private String TEAM_ID;         // 팀ID

    @Id @Column(name = "WEEK")
    private int WEEK;               // 주차

    @Id @Column(name = "MEMBER_ID")
    private String MEMBER_ID;       // 회원ID

    @Id @Column(name = "GOAL_NO")
    private int GOAL_NO;            // 목표번호

    @Column(name = "GOAL")
    private String GOAL;                // 목표내용

    @Column(name = "ORDER_SEQ")
    private int ORDER_SEQ;              // 정렬순서

    @Column(name = "MAIN_GOAL_YN")
    private String MAIN_GOAL_YN;        // 메인목표여부

    @Column(name = "ACHIEVE_STATUS_CD")
    private String ACHIEVE_STATUS_CD;   // 달성상태코드

    @Column(name = "FEEDBACK")
    private String FEEDBACK;            // 피드백내용

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

    public int getGOAL_NO() {
        return GOAL_NO;
    }

    public void setGOAL_NO(int GOAL_NO) {
        this.GOAL_NO = GOAL_NO;
    }

    public String getGOAL() {
        return GOAL;
    }

    public void setGOAL(String GOAL) {
        this.GOAL = GOAL;
    }

    public int getORDER_SEQ() {
        return ORDER_SEQ;
    }

    public void setORDER_SEQ(int ORDER_SEQ) {
        this.ORDER_SEQ = ORDER_SEQ;
    }

    public String getMAIN_GOAL_YN() {
        return MAIN_GOAL_YN;
    }

    public void setMAIN_GOAL_YN(String MAIN_GOAL_YN) {
        this.MAIN_GOAL_YN = MAIN_GOAL_YN;
    }

    public String getACHIEVE_STATUS_CD() {
        return ACHIEVE_STATUS_CD;
    }

    public void setACHIEVE_STATUS_CD(String ACHIEVE_STATUS_CD) {
        this.ACHIEVE_STATUS_CD = ACHIEVE_STATUS_CD;
    }

    public String getFEEDBACK() {
        return FEEDBACK;
    }

    public void setFEEDBACK(String FEEDBACK) {
        this.FEEDBACK = FEEDBACK;
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
        return "Week_Goal{" +
                "TEAM_ID='" + TEAM_ID + '\'' +
                ", WEEK=" + WEEK +
                ", MEMBER_ID='" + MEMBER_ID + '\'' +
                ", GOAL_NO=" + GOAL_NO +
                ", GOAL='" + GOAL + '\'' +
                ", ORDER_SEQ=" + ORDER_SEQ +
                ", MAIN_GOAL_YN='" + MAIN_GOAL_YN + '\'' +
                ", ACHIEVE_STATUS_CD='" + ACHIEVE_STATUS_CD + '\'' +
                ", FEEDBACK='" + FEEDBACK + '\'' +
                ", CHK_ID='" + CHK_ID + '\'' +
                ", CHK_DATE=" + CHK_DATE +
                '}';
    }
}
