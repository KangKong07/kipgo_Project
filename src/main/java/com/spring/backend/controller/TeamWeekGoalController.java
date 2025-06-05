package com.spring.backend.controller;

import com.spring.backend.common.util.JwtUtil;
import com.spring.backend.dto.response.WeekGoalInfoDto;
import com.spring.backend.repository.WeekGoalRepository;
import com.spring.backend.service.WeekGoalService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/team-weekly")
@RequiredArgsConstructor
public class TeamWeekGoalController {

    private final JwtUtil jwtUtil;
    private final WeekGoalService weekGoalService;

    @GetMapping("/goal-feedback/{teamMemberId}/{week}")
    public ResponseEntity<List<WeekGoalInfoDto>> getMyWeekGoalFeedBack(@PathVariable String teamMemberId,
                                                                       @PathVariable int week,
                                                                       @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String memberId = jwtUtil.getMemberIdFromToken(token);
        String teamId = jwtUtil.getClaimFromToken(token, "teamId");

        if (teamId == null || memberId == null) {
            throw new IllegalStateException("로그인 필요한 요청입니다. 로그인 상태 여부를 확인하세요");
        }

        return ResponseEntity.ok(weekGoalService.getTeamWeekGoalInfo(teamId, teamMemberId, week));
    }

}
