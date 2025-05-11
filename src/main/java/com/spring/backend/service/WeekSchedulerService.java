package com.spring.backend.service;

import com.spring.backend.model.*;
import com.spring.backend.repository.TeamMemberRepository;
import com.spring.backend.repository.TeamRepository;
import com.spring.backend.repository.WeekMemberRepository;
import com.spring.backend.repository.WeekRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.spring.backend.common.util.DateUtils.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeekSchedulerService {

    private final TeamRepository teamRepository;
    private final WeekRepository weekRepository;
    private final TeamMemberRepository teamMemberRepository;
    private final WeekMemberRepository weekMemberRepository;

    /*
     * 주차 생성
     *  - 현재일 기준 설정된 시작요일과 일치하는 팀의 주차를 생성한다.
     *  - 주차 생성에 성공하면 참여멤버를 생성한다.
     *  - 팀별 운영시작일과 종료일을 함께 체크한다
     */
    @Transactional
    public void generateWeeklyForToday() {
        Date today = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        String todayCode = LocalDate.now()
                .getDayOfWeek()
                .getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
                .toUpperCase();  // ex) "SAT"

        log.info("# 현재 일자 :: {} / 요일코드 :: {}", today, todayCode);

        // 1️⃣ 오늘 요일에 해당하는 팀 조회
        List<Team> targetTeams = teamRepository.findByWeekStaDayCd(todayCode, today);
        log.info("# 오늘 요일 [{}]에 해당하는 팀 수 :: {}", todayCode, targetTeams.size());

        for (Team team : targetTeams) {
            int nextWeekNo = getNextWeekNumber(team.getTeamId());

            // 주차 시작일자, 종료일자
            LocalDate weekStartDate = LocalDate.now();
            LocalDate weekEndDate = weekStartDate.plusDays(6);

            // Date 타입으로 변환
            Date weekStartDateAsDate = Date.from(
                    weekStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant()
            );
            Date weekEndDateAsDate = toDate(weekEndDate.atTime(23, 59, 59));

            log.info("# weekStartDate :: {} / weekEndDate :: {} / weekStartDateAsDate :: {} / weekEndDateAsDate :: {}", weekStartDate, weekEndDate, weekStartDateAsDate, weekEndDateAsDate);

            // 2️⃣ 주차 데이터 생성
            Week week = Week.builder()
                    .weekId(new WeekId(team.getTeamId(), nextWeekNo))
                    .weekStaDate(weekStartDateAsDate)
                    .weekEndDate(weekEndDateAsDate)
                    .weekStaDayCd(todayCode)
                    .weekEndDayCd(weekEndDate.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
                    .toUpperCase())
                    .vacationYn("N")
                    .chkId("system")
                    .chkDate(new Date())
                    .build();

            weekRepository.save(week);
            log.info("# 주차 생성 완료 → 팀명 :: {}, 생성 주차 :: {}", team.getTeamNm(), nextWeekNo);


            // 3️⃣ 주차 참여자(팀원) 데이터 생성
            List<TeamMember> targetMembers = teamMemberRepository.findValidMembersForWeek(team.getTeamId(), weekStartDateAsDate);

            for (TeamMember member : targetMembers) {
                WeekMemberId weekMemberId = new WeekMemberId(team.getTeamId(), member.getTeamMemberId().getMemberId(), nextWeekNo);

                WeekMember weekMember = WeekMember.builder()
                        .weekMemberId(weekMemberId)
                        .vacationYn("N")
                        .goalRegYn("N")
                        .feedbackRegYn("N")
                        .totAchieveRate(0)
                        .mainGoalUnmet("N")
                        .chkId("system")
                        .chkDate(new Date())
                        .build();

                weekMemberRepository.save(weekMember);
            }

            log.info("# 참여자 생성 완료 → 팀명 :: {}, 생성인원 :: {}", team.getTeamNm(), targetMembers.size());
        }   // ### END ### :: for (Team team : targetTeams) {
    }

    private int getNextWeekNumber(String teamId) {
        return weekRepository.findMaxWeekByTeamId(teamId).orElse(0) + 1;
    }

}
