package com.spring.backend.dto.response;

import com.spring.backend.dto.projection.LoginTeamInfoProjection;
import com.spring.backend.model.Member;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    // * 로그인 사용자 기본정보 (header data)
    private String accessToken;
    private String memberId;
    private String teamId;

    // * 추가 정보
    private String name;    // 사용자 이름
    private String teamNm;  // 팀 이름
    private Integer vacationLimit;  // 팀 휴가 한도(잔여일수)
    private Integer goalRegDeadline;    // 목표작성기한
    private Integer feedbackRegDeadline;    // 피드백작성기한
    private String nickname;    // 팀내 닉네임
    private String teamSetYn;   // 관리기능 사용여부
    private Integer memberVacationLimit; // 개인 휴가 한도(잔여일수)

    public LoginResponse(String accessToken, Member member, LoginTeamInfoProjection mainTeamInfo) {
        this.accessToken = accessToken;
        this.memberId = member.getMemberId();
        this.teamId = member.getMainTeamId();
        this.name = member.getName();

        this.teamNm = mainTeamInfo.getTeamNm();
        this.vacationLimit = mainTeamInfo.getVacationLimit();
        this.goalRegDeadline = mainTeamInfo.getGoalRegDeadline();
        this.feedbackRegDeadline = mainTeamInfo.getFeedbackRegDeadline();
        this.nickname = mainTeamInfo.getNickname();
        this.teamSetYn = mainTeamInfo.getTeamSetYn();
        this.memberVacationLimit = mainTeamInfo.getMemberVacationLimit();

    }

}
