package com.spring.backend.controller;

import com.spring.backend.dto.response.UserMainWeekInfoDto;
import com.spring.backend.service.UserMainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/main")
public class UserMainController {

    private final UserMainService userMainService;

    /*
     * 최근 주차 정보 조회
     * - 팀 ID와 멤버 ID를 통해 현재일 기준 최근 주차 정보 1건을 조회
     * - 직전주차의 피드백 작성여부도 함께 가져온다
     */
    @GetMapping("/week-info")
    public ResponseEntity<UserMainWeekInfoDto> getUserMainWeekInfo(
            @RequestParam String teamId,
            @RequestParam String memberId
    ) {
        return ResponseEntity.ok(userMainService.getUserMainWeekInfo(teamId, memberId));
    }

}
