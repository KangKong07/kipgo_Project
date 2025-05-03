package com.spring.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class WeekGoalId implements Serializable {

    @Column(name = "TEAM_ID")
    private String teamId;

    @Column(name = "MEMBER_ID")
    private String memberId;

    @Column(name = "WEEK")
    private int week;

    @Column(name = "GOAL_NO")
    private int goalNo;

    public WeekGoalId() {}

    public WeekGoalId(String teamId, String memberId, int week, int goalNo) {
        this.teamId = teamId;
        this.memberId = memberId;
        this.week = week;
        this.goalNo = goalNo;
    }

    // Getter , Setter
    public String getTeamId() { return teamId; }
    public void setTeamId(String teamId) { this.teamId = teamId; }

    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }

    public int getWeek() { return week; }
    public void setWeek(int week) { this.week = week; }

    public int getGoalNo() { return goalNo; }
    public void setGoalNo(int goalNo) { this.goalNo = goalNo; }

    // equals 구현
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(!(o instanceof WeekGoalId)) return false;
        WeekGoalId that = (WeekGoalId) o;
        return Objects.equals(teamId, that.teamId)
                && Objects.equals(memberId, that.memberId)
                && week == that.week
                && goalNo == that.goalNo;
    }

    // hashCode 구현
    @Override
    public int hashCode() { return Objects.hash(teamId, memberId, week, goalNo); }


}
