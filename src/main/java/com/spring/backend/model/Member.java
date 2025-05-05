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

}
