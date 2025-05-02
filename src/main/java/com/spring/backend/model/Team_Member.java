package com.spring.backend.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString(exclude = {"chkId", "chkDate"})
@Entity
@Table(name = "TEAM_MEMBER")
public class Team_Member {

    @Id @Column(name = "TEAM_ID")
    private String teamId;         // 팀ID

    @Id @Column(name = "MEMBER_ID")
    private String memberId;       // 회원ID

    @Column(name = "NICKNAME")
    private String nickName;        // 팀내 별명

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

}
