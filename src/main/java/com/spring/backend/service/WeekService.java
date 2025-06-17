package com.spring.backend.service;

import com.spring.backend.dto.request.VacationRequestDto;
import com.spring.backend.dto.response.WeekInfoDto;
import com.spring.backend.model.*;
import com.spring.backend.repository.TeamMemberRepository;
import com.spring.backend.repository.TeamRepository;
import com.spring.backend.repository.WeekRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
@RequiredArgsConstructor
public class WeekService {

    private final WeekRepository weekRepository;
    private final WeekMemberService weekMemberService;
    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;

    public List<Week> findAll() {
        return weekRepository.findAll();
    }

    public Optional<Week> findById(WeekId weekId) {
        return weekRepository.findById(weekId);
    }

    public Week save(Week week) {
        return weekRepository.save(week);
    }

    public void deleteById(WeekId weekId) {
        weekRepository.deleteById(weekId);
    }

    public List<WeekInfoDto> getWeekListInfo(String teamId, String memberId) {

        // 1. 팀원 참여 종료 검증 - 팀멤버의 참여 종료일 지났는지 여부 체크
        TeamMember searchTeamMember = teamMemberRepository.findById(new TeamMemberId(teamId, memberId))
                .orElseThrow(() -> new RuntimeException("팀원 정보가 존재하지 않습니다."));
        Date joinEndDate = searchTeamMember.getJoinEndDate();
        Date now = new Date();
        if(joinEndDate != null &&  now.after(joinEndDate)) {
            throw new IllegalArgumentException("팀 참여 종료일이 지나 목표를 조회할 수 없습니다.");
        }

        // 2. 목표 데이터 조회 후 리턴
        return weekRepository.findWeekListInfo(teamId, memberId);

    }

    /**
     * 나의 휴가상태 저장
     * @param teamId
     * @param memberId
     * @param param
     * @author KangKong07
     */
    @Transactional
    public WeekMember saveVacation(String teamId, String memberId, VacationRequestDto param) {

        // 1. 팀원 검증
        TeamMember searchTeamMember = teamMemberRepository.findById(new TeamMemberId(teamId, memberId))
                .orElseThrow(() -> new NoSuchElementException("팀원 정보가 존재하지 않습니다."));

        // 2. 팀 정보 조회
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new NoSuchElementException("팀 정보를 찾을 수 없습니다."));

        // 3. 현재 휴가 잔여 일수 검증
        searchTeamMember.decreaseVacationLimit(); // 내부에서 -1 처리
        teamMemberRepository.save(searchTeamMember); // 변경 감지되면 생략 가능

        // 3. 목표 작성 기한 검증
        LocalDate weekStaDate = LocalDate.parse(param.getWeekStaDate());
        validateGoalDeadline(weekStaDate, team.getGoalRegDeadline());

        // 4. 주차 회원 정보 조회 및 업데이트
        WeekMemberId weekMemberId = new WeekMemberId(teamId, memberId, param.getWeek());
        return weekMemberService.findById(weekMemberId)
                .map(existingWeekMember -> weekMemberService.save(existingWeekMember.toBuilder()
                        .vacationYn("Y")
                        .goalRegYn("Y")
                        .totAchieveRate(100)
                        .chkId(memberId)
                        .chkDate(new Date())
                        .build()))
                .orElseThrow(() -> new NoSuchElementException("해당 주차의 WeekMember 정보를 찾을 수 없습니다." +
                        " (teamId: " + teamId + ", memberId: " + memberId + ")"));
    }

    // 휴가일수 검증 로직
    private void validateGoalDeadline(LocalDate startDate, int deadlineDays) {

        LocalDate deadline = startDate.plusDays(deadlineDays);
        LocalDate today = LocalDate.now();
        System.out.println("# validateGoalDeadline deadline :: " + deadline + " / today :: " + today);

        if (today.isAfter(deadline)) {
            throw new IllegalArgumentException("목표 등록 기한이 지났습니다. (기한: " + deadline + "까지)");
        }
    }

}
