package com.spring.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
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
    private Date chkDate;

    public WeekGoal(String teamId,
                    String memberId,
                    int week,
                    int goalNo,
                    String goal,
                    int orderSeq,
                    String mainGoalYn,
                    String achieveStatusCd,
                    String feedback,
                    Date goalChkDate) {
        weekGoalId = new WeekGoalId(teamId, memberId, week, goalNo);
        this.goal = goal;
        this.orderSeq = orderSeq;
        this.mainGoalYn = mainGoalYn;
        this.achieveStatusCd = achieveStatusCd;
        this.feedback = feedback;
        this.chkDate = goalChkDate;
    }


    /* [ setter ] */
    public void setGoal(String goal) {
        this.goal = goal;
    }
    public void setOrderSeq(int orderSeq) {
        this.orderSeq = orderSeq;
    }
    public void setMainGoalYn(String mainGoalYn) {
        this.mainGoalYn = mainGoalYn;
    }
    public void setAchieveStatusCd(String achieveStatusCd) {
        this.achieveStatusCd = achieveStatusCd;
    }
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
    public void setChkId(String chkId) {
        this.chkId = chkId;
    }
    public void setChkDate(Date chkDate) {
        this.chkDate = chkDate;
    }
}
