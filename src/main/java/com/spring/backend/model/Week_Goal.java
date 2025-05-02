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
public class Week_Goal {

    @Id @Column(name = "TEAM_ID")
    private String teamId;         // 팀ID

    @Id @Column(name = "WEEK")
    private int week;               // 주차

    @Id @Column(name = "MEMBER_ID")
    private String memberId;       // 회원ID

    @Id @Column(name = "GOAL_NO")
    private int goalNo;            // 목표번호

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
