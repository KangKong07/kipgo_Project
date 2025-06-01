package com.spring.backend.dto.response;

import com.spring.backend.model.Member;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String accessToken;
    private String memberId;
    private String mainTeamId;
    private String name;

    public LoginResponse(String accessToken, Member member) {
        this.accessToken = accessToken;
        this.memberId = member.getMemberId();
        this.name = member.getName();
        this.mainTeamId = member.getMainTeamId();
    }

}
