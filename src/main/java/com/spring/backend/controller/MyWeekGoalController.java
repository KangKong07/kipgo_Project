package com.spring.backend.controller;

import com.spring.backend.dto.response.ApiResponse;
import com.spring.backend.dto.response.WeekGoalInfoDto;
import com.spring.backend.model.WeekGoal;
import com.spring.backend.service.WeekGoalService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/my-weekly")
@RequiredArgsConstructor
public class MyWeekGoalController {

    private final WeekGoalService weekGoalService;

    @GetMapping("/goal-feedback/{week}")
    public ResponseEntity<List<WeekGoalInfoDto>> getMyWeekGoalFeedBack(@PathVariable int week,
                                                                 HttpSession httpSession) {
        String teamId = (String) httpSession.getAttribute("teamId");
        String memberId = (String) httpSession.getAttribute("memberId");

        if (teamId == null || memberId == null) {
            throw new IllegalStateException("로그인 필요한 요청입니다. 로그인 상태 여부를 확인하세요");
        }

        return ResponseEntity.ok(weekGoalService.getMyWeekGoalInfo(teamId, memberId, week));
    }

    @PostMapping("/goal/save/{week}")
    public ResponseEntity<ApiResponse<List<WeekGoalInfoDto>>> saveMyWeekGoal(@PathVariable int week,
                                                                             @RequestBody List<WeekGoal> weekGoalList,
                                                                             HttpSession httpSession) {
        String teamId = (String) httpSession.getAttribute("teamId");
        String memberId = (String) httpSession.getAttribute("memberId");

        if (teamId == null || memberId == null) {
            ApiResponse<List<WeekGoalInfoDto>> response = ApiResponse.failure("로그인 필요한 요청입니다. 로그인 상태 여부를 확인하세요");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        ApiResponse<List<WeekGoalInfoDto>> result = weekGoalService.saveMyWeekGoal(teamId, memberId, week, weekGoalList);
        if ("success".equals(result.getStatus())) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }


    @DeleteMapping("/goal/delete/{week}/{goalno}")
    public ResponseEntity<ApiResponse<List<WeekGoalInfoDto>>> deleteMyWeekGoal(@PathVariable int week,
                                                                               @PathVariable int goalno,
                                                                               HttpSession httpSession) {
        String teamId = (String) httpSession.getAttribute("teamId");
        String memberId = (String) httpSession.getAttribute("memberId");

        if (teamId == null || memberId == null) {
            ApiResponse<List<WeekGoalInfoDto>> response = ApiResponse.failure("로그인 필요한 요청입니다. 로그인 상태 여부를 확인하세요");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        ApiResponse<List<WeekGoalInfoDto>> result = weekGoalService.deleteMyWeekGoal(teamId, memberId, week, goalno);
        if ("success".equals(result.getStatus())) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    @PostMapping("/feedback/save")
    public ResponseEntity<ApiResponse<List<WeekGoalInfoDto>>> saveFeedback(@PathVariable int week,
                                                                     @RequestBody List<WeekGoal> weekGoalList,
                                                                     HttpSession httpSession) {
        String teamId = (String) httpSession.getAttribute("teamId");
        String memberId = (String) httpSession.getAttribute("memberId");

        if (teamId == null || memberId == null) {
            ApiResponse<List<WeekGoalInfoDto>> response = ApiResponse.failure("로그인 필요한 요청입니다. 로그인 상태 여부를 확인하세요");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        ApiResponse<List<WeekGoalInfoDto>> result = weekGoalService.saveFeedback(teamId, memberId, week, weekGoalList);
        if ("success".equals(result.getStatus())) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }
}
