package com.spring.backend.controller;

import com.spring.backend.dto.response.WeekGoalInfoDto;
import com.spring.backend.repository.WeekGoalRepository;
import com.spring.backend.service.WeekGoalService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/team-weekly")
@RequiredArgsConstructor
public class TeamWeekGoalController {

    private final WeekGoalService weekGoalService;

    @GetMapping("/goal-feedback/{teamMemberId}/{week}")
    public ResponseEntity<List<WeekGoalInfoDto>> getMyWeekGoalFeedBack(@PathVariable String teamMemberId,
                                                                       @PathVariable int week,
                                                                       HttpSession httpSession) {
        String teamId = (String) httpSession.getAttribute("teamId");
        String memberId = (String) httpSession.getAttribute("memberId");

        if (teamId == null || memberId == null) {
            throw new IllegalStateException("로그인 필요한 요청입니다. 로그인 상태 여부를 확인하세요");
        }

        return ResponseEntity.ok(weekGoalService.getTeamWeekGoalInfo(teamId, teamMemberId, week));
    }

}
