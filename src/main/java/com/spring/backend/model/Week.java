package com.spring.backend.model;

import jakarta.persistence.*;
import jdk.jshell.Snippet;

import java.util.Date;

@Entity
@Table(name = "WEEK")
public class Week {

    @EmbeddedId
    private WeekId weekId;

    @Column(name = "WEEK_STA_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date weekStaDate;         // 시작일자

    @Column(name = "WEEK_END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date weekEndDate;         // 종료일자

    @Column(name = "WEEK_STA_DAY_CD")
    private String weekStaDayCd;     // 시작요일코드

    @Column(name = "WEEK_END_DAY_CD")
    private String weekEndDayCd;     // 종료요일코드

    @Column(name = "VACATION_YN")
    private String vacationYn;         // 휴가여부

    @Column(name = "CHK_ID")
    private String chkId;              // 최종작업자

    @Column(name = "CHK_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date chkDate;              // 최종작업시간


    public WeekId getWeekId() {
        return weekId;
    }

    public void setWeekId(WeekId id) {
        this.weekId = id;
    }

    public Date getWeekStaDate() {
        return weekStaDate;
    }

    public void setWeekStaDate(Date weekStaDate) {
        this.weekStaDate = weekStaDate;
    }

    public Date getWeekEndDate() {
        return weekEndDate;
    }

    public void setWeekEndDate(Date weekEndDate) {
        this.weekEndDate = weekEndDate;
    }

    public String getWeekStaDayCd() {
        return weekStaDayCd;
    }

    public void setWeekStaDayCd(String weekStaDayCd) {
        this.weekStaDayCd = weekStaDayCd;
    }

    public String getWeekEndDayCd() {
        return weekEndDayCd;
    }

    public void setWeekEndDayCd(String weekEndDayCd) {
        this.weekEndDayCd = weekEndDayCd;
    }

    public String getVacationYn() {
        return vacationYn;
    }

    public void setVacationYn(String vacationYn) {
        this.vacationYn = vacationYn;
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
        return "Week {" +
                "weekId=" + weekId +
                ", weekStaDate=" + weekStaDate +
                ", weekEndDate=" + weekEndDate +
                ", weekStaDayCd='" + weekStaDayCd + '\'' +
                ", weekEndDayCd='" + weekEndDayCd + '\'' +
                ", vacationYn='" + vacationYn + '\'' +
                ", chkId='" + chkId + '\'' +
                ", chkDate=" + chkDate +
                ' }';
    }
}
