package com.spring.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
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
                "}";
    }
}
