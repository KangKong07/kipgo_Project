package com.spring.backend.controller;

import com.spring.backend.common.util.JwtUtil;
import com.spring.backend.dto.request.VacationRequestDto;
import com.spring.backend.dto.response.WeekInfoDto;
import com.spring.backend.model.Week;
import com.spring.backend.model.WeekId;
import com.spring.backend.model.WeekMember;
import com.spring.backend.service.WeekService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/my-weekly")
@RequiredArgsConstructor
public class WeekController {

    private final JwtUtil jwtUtil;
    private final WeekService weekService;

    /*
     * 참여하는 팀 내 주차 목록 리스트 조회
     */
    @GetMapping("/week-list")
    public ResponseEntity<List<WeekInfoDto>> getWeekListInfo(@RequestHeader("Authorization") String authHeader) {

        String token = authHeader.replace("Bearer ", "");
        String memberId = jwtUtil.getMemberIdFromToken(token);
        String teamId = jwtUtil.getClaimFromToken(token, "teamId");

        if (teamId == null || memberId == null) {
            throw new IllegalStateException("로그인 필요한 요청입니다. 로그인 상태 여부를 확인하세요");
        }

        List<WeekInfoDto> weekInfoDto = weekService.getWeekListInfo(teamId, memberId);
        return ResponseEntity.ok(weekInfoDto);
    }

    /*
     * 참여 하는 팀 내 휴가 설정 변경
     */
    @PutMapping("/vacation/save")
    public ResponseEntity<WeekMember> saveVacation(@RequestBody VacationRequestDto param,
                                                   @RequestHeader("Authorization") String authHeader) {

        // 1. 헤더정보 추출
        String token = authHeader.replace("Bearer ", "");
        String memberId = jwtUtil.getMemberIdFromToken(token);
        String teamId = jwtUtil.getClaimFromToken(token, "teamId");

        // 2. 요청 파라미터에서 weekId 생성
//        WeekId weekId = new WeekId(teamId, param.getWeek());

        WeekMember updated = weekService.saveVacation(teamId, memberId, param);
        return ResponseEntity.ok(updated);
    }

    @GetMapping
    public List<Week> findAll() {
        return weekService.findAll();
    }

    @GetMapping("/{teamId}/{week}")
    public ResponseEntity<Week> findById(@PathVariable String teamId,
                                         @PathVariable int week) {
        WeekId id = new WeekId(teamId, week);
        return weekService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Week> create(@RequestBody Week week) {
        Week savedWeek = weekService.save(week);
        return ResponseEntity.ok(savedWeek);
    }

    @PutMapping("/{teamId}/{week}")
    public ResponseEntity<Week> update(@PathVariable String teamId,
                                       @PathVariable int week,
                                       @RequestBody Week weekRequest) {
        WeekId id = new WeekId(teamId, week);
        return weekService.findById(id)
                .map(existingWeek -> {
                    Week updatedWeek = Week.builder()
                            .weekId(existingWeek.getWeekId())
                            .weekStaDate(weekRequest.getWeekStaDate())
                            .weekEndDate(weekRequest.getWeekEndDate())
                            .weekStaDayCd(weekRequest.getWeekStaDayCd())
                            .weekEndDayCd(weekRequest.getWeekEndDayCd())
                            .vacationYn(weekRequest.getVacationYn())
                            .chkId(weekRequest.getChkId())
                            .chkDate(new Date())
                            .build();

                    Week savedWeek = weekService.save(updatedWeek);
                    return ResponseEntity.ok(savedWeek);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{teamId}/{week}")
    public ResponseEntity<Week> delete(@PathVariable String teamId, @PathVariable int week) {
        WeekId id = new WeekId(teamId, week);
        weekService.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
