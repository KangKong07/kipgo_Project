package com.spring.backend;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "TEAM_MEMBER")
public class Team_Member {

    @Id @Column(name = "TEAM_ID")
    private String TEAM_ID;         // 팀ID

    @Id @Column(name = "MEMBER_ID")
    private String MEMBER_ID;       // 회원ID

    @Column(name = "NICKNAME")
    private String NICKNAME;        // 팀내 별명

    @Column(name = "JOIN_STA_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date JOIN_STA_DATE;   // 참여시작일

    @Column(name = "JOIN_END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date JOIN_END_DATE;   // 참여종료일

    @Column(name = "PROFILE_FILE_ID")
    private String PROFILE_FILE_ID; // 프로필 이미지

    @Column(name = "TEAM_SET_YN")
    private String TEAM_SET_YN;     // 팀관리 가능여부

    @Column(name = "CHK_ID")
    private String CHK_ID;          // 최종작업자

    @Column(name = "CHK_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date CHK_DATE;          // 최종작업시간

    public String getTEAM_ID() {
        return TEAM_ID;
    }

    public void setTEAM_ID(String TEAM_ID) {
        this.TEAM_ID = TEAM_ID;
    }

    public String getMEMBER_ID() {
        return MEMBER_ID;
    }

    public void setMEMBER_ID(String MEMBER_ID) {
        this.MEMBER_ID = MEMBER_ID;
    }

    public String getNICKNAME() {
        return NICKNAME;
    }

    public void setNICKNAME(String NICKNAME) {
        this.NICKNAME = NICKNAME;
    }

    public Date getJOIN_STA_DATE() {
        return JOIN_STA_DATE;
    }

    public void setJOIN_STA_DATE(Date JOIN_STA_DATE) {
        this.JOIN_STA_DATE = JOIN_STA_DATE;
    }

    public Date getJOIN_END_DATE() {
        return JOIN_END_DATE;
    }

    public void setJOIN_END_DATE(Date JOIN_END_DATE) {
        this.JOIN_END_DATE = JOIN_END_DATE;
    }

    public String getPROFILE_FILE_ID() {
        return PROFILE_FILE_ID;
    }

    public void setPROFILE_FILE_ID(String PROFILE_FILE_ID) {
        this.PROFILE_FILE_ID = PROFILE_FILE_ID;
    }

    public String getTEAM_SET_YN() {
        return TEAM_SET_YN;
    }

    public void setTEAM_SET_YN(String TEAM_SET_YN) {
        this.TEAM_SET_YN = TEAM_SET_YN;
    }

    public String getCHK_ID() {
        return CHK_ID;
    }

    public void setCHK_ID(String CHK_ID) {
        this.CHK_ID = CHK_ID;
    }

    public Date getCHK_DATE() {
        return CHK_DATE;
    }

    public void setCHK_DATE(Date CHK_DATE) {
        this.CHK_DATE = CHK_DATE;
    }

    @Override
    public String toString() {
        return "Team_Member{" +
                "TEAM_ID='" + TEAM_ID + '\'' +
                ", MEMBER_ID='" + MEMBER_ID + '\'' +
                ", NICKNAME='" + NICKNAME + '\'' +
                ", JOIN_STA_DATE=" + JOIN_STA_DATE +
                ", JOIN_END_DATE=" + JOIN_END_DATE +
                ", PROFILE_FILE_ID='" + PROFILE_FILE_ID + '\'' +
                ", TEAM_SET_YN='" + TEAM_SET_YN + '\'' +
                ", CHK_ID='" + CHK_ID + '\'' +
                ", CHK_DATE=" + CHK_DATE +
                '}';
    }
}
