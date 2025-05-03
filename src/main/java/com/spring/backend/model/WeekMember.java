package com.spring.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString(exclude = {"chkId" ,"chkDate"})
@Entity
@Table(name = "WEEK_MEMBER")
public class WeekMember {

    @EmbeddedId
    private WeekMemberId weekMemberId;  // 복합키(팀ID/주차/회원ID)

    @Column(name = "VACATION_YN")
    private String vacationYn;     // 휴가여부

    @Column(name = "GOAL_REG_YN")
    private String goalRegYn;     // 목표작성여부

    @Column(name = "FEEDBACK_REG_YN")
    private String feedbackRegYn; // 피드백작성여부

    @Column(name = "TOT_ACHIEVE_RATE")
    private int totAchieveRate;   // 총달성률

    @Column(name = "COMMENT")
    private String comment;         // 총평

    @Column(name = "MAIN_GOAL_UNMET")
    private String mainGoalUnmet; // 메인목표 미달성여부

    @Column(name = "CHK_ID")
    private String chkId;          // 최종작업자

    @Column(name = "CHK_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date chkDate;          // 최종작업시간

}
