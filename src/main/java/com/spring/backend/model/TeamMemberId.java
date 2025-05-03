package com.spring.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TeamMemberId implements Serializable {

    @Column(name = "TEAM_ID")
    private String teamId;

    @Column(name = "MEMBER_ID")
    private String memberId;

    public TeamMemberId() {}

    public TeamMemberId(String teamId, String memberId) {
        this.teamId = teamId;
        this.memberId = memberId;
    }

    // Getter, Setter
    public String getTeamId() { return teamId; }
    public void setTeamId(String teamId) { this.teamId = teamId; }

    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }

    // * equals & hashCode 구현
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeamMemberId)) return false;
        TeamMemberId that = (TeamMemberId) o;
        return Objects.equals(teamId, that.teamId) && Objects.equals(memberId, that.memberId);
    }

    @Override
    public int hashCode() { return Objects.hash(teamId, memberId); }

}
