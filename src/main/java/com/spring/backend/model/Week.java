package com.spring.backend.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "WEEK")
public class Week {

    @Id @Column(name = "TEAM_ID")
    private String TEAM_ID;             // 팀ID

    @Id @Column(name = "WEEK")
    private int WEEK;                   // 주차

    @Column(name = "WEEK_STA_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date WEEK_STA_DATE;         // 시작일자

    @Column(name = "WEEK_END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date WEEK_END_DATE;         // 종료일자

    @Column(name = "WEEK_STA_DAY_CD")
    private String WEEK_STA_DAY_CD;     // 시작요일코드

    @Column(name = "WEEK_END_DAY_CD")
    private String WEEK_END_DAY_CD;     // 종료요일코드

    @Column(name = "VACATION_YN")
    private String VACATION_YN;         // 휴가여부

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

    public Date getWEEK_STA_DATE() {
        return WEEK_STA_DATE;
    }

    public void setWEEK_STA_DATE(Date WEEK_STA_DATE) {
        this.WEEK_STA_DATE = WEEK_STA_DATE;
    }

    public Date getWEEK_END_DATE() {
        return WEEK_END_DATE;
    }

    public void setWEEK_END_DATE(Date WEEK_END_DATE) {
        this.WEEK_END_DATE = WEEK_END_DATE;
    }

    public String getWEEK_STA_DAY_CD() {
        return WEEK_STA_DAY_CD;
    }

    public void setWEEK_STA_DAY_CD(String WEEK_STA_DAY_CD) {
        this.WEEK_STA_DAY_CD = WEEK_STA_DAY_CD;
    }

    public String getWEEK_END_DAY_CD() {
        return WEEK_END_DAY_CD;
    }

    public void setWEEK_END_DAY_CD(String WEEK_END_DAY_CD) {
        this.WEEK_END_DAY_CD = WEEK_END_DAY_CD;
    }

    public String getVACATION_YN() {
        return VACATION_YN;
    }

    public void setVACATION_YN(String VACATION_YN) {
        this.VACATION_YN = VACATION_YN;
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
        return "Week{" +
                "TEAM_ID='" + TEAM_ID + '\'' +
                ", WEEK=" + WEEK +
                ", WEEK_STA_DATE=" + WEEK_STA_DATE +
                ", WEEK_END_DATE=" + WEEK_END_DATE +
                ", WEEK_STA_DAY_CD='" + WEEK_STA_DAY_CD + '\'' +
                ", WEEK_END_DAY_CD='" + WEEK_END_DAY_CD + '\'' +
                ", VACATION_YN='" + VACATION_YN + '\'' +
                ", CHK_ID='" + CHK_ID + '\'' +
                ", CHK_DATE=" + CHK_DATE +
                '}';
    }
}
