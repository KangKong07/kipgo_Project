package com.spring.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
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

    /* [ setter ] */
    public void setWeekStaDate(Date weekStaDate) {
        this.weekStaDate = weekStaDate;
    }
    public void setWeekEndDate(Date weekEndDate) {
        this.weekEndDate = weekEndDate;
    }
    public void setWeekStaDayCd(String weekStaDayCd) {
        this.weekStaDayCd = weekStaDayCd;
    }
    public void setWeekEndDayCd(String weekEndDayCd) {
        this.weekEndDayCd = weekEndDayCd;
    }
    public void setVacationYn(String vacationYn) {
        this.vacationYn = vacationYn;
    }
    public void setChkId(String chkId) {
        this.chkId = chkId;
    }
    public void setChkDate(Date chkDate) {
        this.chkDate = chkDate;
    }
    public void setTeamId(String teamId) {
        this.weekId.setTeamId(teamId);
    }
    public void setWeek(int week) {
        this.weekId.setWeek(week);
    }
}
