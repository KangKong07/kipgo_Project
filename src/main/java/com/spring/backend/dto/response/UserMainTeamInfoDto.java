package com.spring.backend.dto.response;


/*
 * 내 소속 팀 리스트 DTO
 */
public interface UserMainTeamInfoDto {

    String getTeamId();     // 팀ID
    String getTeamName();   // 팀 명
    String getNickName();   // 팀 내 별명

}
