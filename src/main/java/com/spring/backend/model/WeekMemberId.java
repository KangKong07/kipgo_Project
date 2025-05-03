package com.spring.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class WeekMemberId implements Serializable {

    @Column(name = "TEAM_ID")
    private String teamId;

    @Column(name = "WEEK")
    private int week;

    @Column(name = "MEMBER_ID")
    private String memberId;

    public WeekMemberId() {}

    public WeekMemberId(String teamId, String memberId, int week) {
        this.teamId = teamId;
        this.memberId = memberId;
        this.week = week;
    }

    public String getTeamId() { return teamId; }
    public void setTeamId(String teamId) { this.teamId = teamId; }

    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }

    public int getWeek() { return week; }
    public void setWeek(int week) { this.week = week; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WeekMemberId)) return false;
        WeekMemberId that = (WeekMemberId) o;
        return week == that.week &&
                Objects.equals(memberId, that.memberId) &&
                Objects.equals(teamId, that.teamId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId, week, memberId);
    }
}
