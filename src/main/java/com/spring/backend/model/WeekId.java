package com.spring.backend.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Column;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class WeekId implements Serializable {

    @Column(name = "TEAM_ID")
    private String teamId;

    @Column(name = "WEEK")
    private int week;

    public WeekId() {}

    public WeekId(String teamId, int week) {
        this.teamId = teamId;
        this.week = week;
    }

    // * Getter/Setter
    public String getTeamId() { return teamId; }
    public void setTeamId(String teamId) { this.teamId = teamId; }

    public int getWeek() { return week; }
    public void setWeek(int week) { this.week = week; }

    // * equals & hashCode 필수!!
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WeekId)) return false;
        WeekId that = (WeekId) o;
        return week == that.week && Objects.equals(teamId, that.teamId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId, week);
    }
}
