package com.spring.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "MEMBER")
public class Member {

    @Id
    @Column(name = "MEMBER_ID")
    private String memberId;   // 회원ID

    @Column(name = "MEMBER_PWD")
    private String memberPwd;  // 비밀번호

    @Column(name = "NAME")
    private String name;        // 이름

    @Column(name = "TEL_NO")
    private String telNo;      // 연락처

    @Column(name = "EMAIL")
    private String email;       // 이메일주소

    @Column(name = "DELETE_YN")
    private String deleteYn;   // 삭제여부

    @Column(name = "JOIN_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date joinDate;     // 가입일자

    @Column(name = "CHK_ID")
    private String chkId;      // 최종작업자

    @Column(name = "CHK_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date chkDate;      // 최종작업시간

    @Column(name = "MAIN_TEAMID")
    private String mainTeamId;  // 대표팀 ID


    /* [ setter ] */
    public void setName(String name) {
        this.name = name;
    }
    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setDeleteYn(String deleteYn) {
        this.deleteYn = deleteYn;
    }
    public void setChkDate(Date chkDate) {
        this.chkDate = chkDate;
    }
    public void setMemberPwd(String memberPwd) {
        this.memberPwd = memberPwd;
    }
}
