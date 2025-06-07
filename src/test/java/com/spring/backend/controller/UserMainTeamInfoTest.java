package com.spring.backend.controller;

import com.spring.backend.dto.response.UserMainTeamInfoDto;
import com.spring.backend.service.UserMainService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserMainTeamInfoTest {

    @Autowired
    private UserMainService userMainService;

    @Test
    @DisplayName("rudgns3456 멤버의 소속 팀 리스트를 조회하면 팀 정보가 반환된다")
    void testGetUserMainTeamInfo_existingMember() {
        // given
        String memberId = "rudgns3456";

        // when
        List<UserMainTeamInfoDto> teamInfoList = userMainService.getUserMainTeamInfo(memberId);

        // then
        assertNotNull(teamInfoList, "반환된 리스트는 null이 아니어야 한다");
        assertFalse(teamInfoList.isEmpty(), "소속된 팀이 있어야 하므로 리스트는 비어있지 않아야 한다");

        for (UserMainTeamInfoDto team : teamInfoList) {
            System.out.println("팀 ID: " + team.getTeamId());
            System.out.println("팀 이름: " + team.getTeamName());
            System.out.println("닉네임: " + team.getNickName());
            System.out.println("----------");
        }
    }
}
