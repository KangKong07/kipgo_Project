package com.spring.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString(exclude = {"chkId", "chkDate"})
@Entity
@Table(name = "WEEK_GOAL")
public class WeekGoal {

    @EmbeddedId
    private WeekGoalId weekGoalId;         // 복합키(팀ID/회원ID/주차/목표번호)

    @Column(name = "GOAL")
    private String goal;                // 목표내용

    @Column(name = "ORDER_SEQ")
    private int orderSeq;              // 정렬순서

    @Column(name = "MAIN_GOAL_YN")
    private String mainGoalYn;        // 메인목표여부

    @Column(name = "ACHIEVE_STATUS_CD")
    private String achieveStatusCd;   // 달성상태코드

    @Column(name = "FEEDBACK")
    private String feedback;            // 피드백내용

    @Column(name = "CHK_ID")
    private String chkId;              // 최종작업자

    @Column(name = "CHK_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date chkDate;              // 최종작업시간

}
