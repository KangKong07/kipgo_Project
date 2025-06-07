package com.spring.backend.controller;

import com.spring.backend.common.exception.UserNotFoundException;
import com.spring.backend.common.util.JwtUtil;
import com.spring.backend.dto.response.UserMainTeamInfoDto;
import com.spring.backend.dto.response.UserMainWeekInfoDto;
import com.spring.backend.dto.response.WeekInfoDto;
import com.spring.backend.service.UserMainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/main")
public class UserMainController {

    private final JwtUtil jwtUtil;
    private final UserMainService userMainService;

    /*
     * 최근 주차 정보 조회
     * - 팀 ID와 멤버 ID를 통해 현재일 기준 최근 주차 정보 1건을 조회
     * - 직전주차의 피드백 작성여부도 함께 가져온다
     */
    @GetMapping("/week-info")
    public ResponseEntity<UserMainWeekInfoDto> getUserMainWeekInfo(
            @RequestHeader("Authorization") String authHeader
    ) {

        String token = authHeader.replace("Bearer ", "");
        String memberId = jwtUtil.getMemberIdFromToken(token);
        String teamId = jwtUtil.getClaimFromToken(token, "teamId");

        if (teamId == null || memberId == null) {
            throw new UserNotFoundException("로그인 필요한 요청입니다. 로그인 상태 여부를 확인하세요");
        }

        return ResponseEntity.ok(userMainService.getUserMainWeekInfo(teamId, memberId));
    }

    @GetMapping("/team-info")
    public ResponseEntity<List<UserMainTeamInfoDto>> getUserMainTeamInfo(
            @RequestHeader("Authorization") String authHeader
    ) {
        String token = authHeader.replace("Bearer ", "");
        String memberId = jwtUtil.getMemberIdFromToken(token);

        if (memberId == null) {
            throw new UserNotFoundException("로그인 필요한 요청입니다. 로그인 상태 여부를 확인하세요");
        }
        List<UserMainTeamInfoDto> userMainTeamInfoDto = userMainService.getUserMainTeamInfo(memberId);
        return ResponseEntity.ok(userMainTeamInfoDto);
    }
}
