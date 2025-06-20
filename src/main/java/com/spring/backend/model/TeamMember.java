package com.spring.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "TEAM_MEMBER")
public class TeamMember {

    @EmbeddedId
    private TeamMemberId teamMemberId;         // 복합키 클래스(팀ID, 회원ID)

    @Column(name = "NICKNAME")
    private String nickname;        // 팀내 별명

    @Column(name = "JOIN_STA_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date joinStaDate;   // 참여시작일

    @Column(name = "JOIN_END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date joinEndDate;   // 참여종료일

    @Column(name = "PROFILE_FILE_ID")
    private String profileFileId; // 프로필 이미지

    @Column(name = "TEAM_SET_YN")
    private String teamSetYn;     // 팀관리 가능여부

    @Column(name = "CHK_ID")
    private String chkId;          // 최종작업자

    @Column(name = "CHK_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date chkDate;          // 최종작업시간

    @Column(name = "MEMBER_VACATION_LIMIT")
    private int memberVacationLimit;    // 멤버 휴가 잔여 일수


    /* [ setter ] */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setJoinStaDate(Date joinStaDate) {
        this.joinStaDate = joinStaDate;
    }

    public void setJoinEndDate(Date joinEndDate) {
        this.joinEndDate = joinEndDate;
    }

    public void setProfileFileId(String profileFileId) {
        this.profileFileId = profileFileId;
    }

    public void setTeamSetYn(String teamSetYn) {
        this.teamSetYn = teamSetYn;
    }

    public void setChkId(String chkId) {
        this.chkId = chkId;
    }

    public void setChkDate(Date chkDate) {
        this.chkDate = chkDate;
    }

    // 휴가 제한 일수 차감 메서드
    public void decreaseVacationLimit() {
        if (memberVacationLimit <= 0) {
            throw new IllegalStateException("현재 잔여 휴가가 없습니다.");
        }
        this.memberVacationLimit--;
    }
}
