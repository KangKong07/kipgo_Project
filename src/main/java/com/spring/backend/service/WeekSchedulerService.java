package com.spring.backend.service;

import com.spring.backend.model.Team;
import com.spring.backend.model.Week;
import com.spring.backend.model.WeekId;
import com.spring.backend.repository.TeamRepository;
import com.spring.backend.repository.WeekRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeekSchedulerService {

    private final TeamRepository teamRepository;
    private final WeekRepository weekRepository;

    @Transactional
    public void generateWeeklyForToday() {
        String todayCode = LocalDate.now().getDayOfWeek().name();  // 예: "MON"

        List<Team> targetTeams = teamRepository.findByWeekStaDayCd(todayCode);

        log.info("# 오늘 요일 [{}]에 해당하는 팀 수 :: {}", todayCode, targetTeams.size());

        for (Team team : targetTeams) {
            int nextWeekNo = getNextWeekNumber(team.getTeamId());

            LocalDate weekStartDate = getWeekStartDate(team.getWeekStaDayCd());
            Date weekStartDateAsDate = Date.from(weekStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            LocalDate weekEndDate = weekStartDate.plusDays(6);
            Date weekEndDateAsDate = Date.from(
                    weekEndDate.atTime(23, 59, 59)
                            .atZone(ZoneId.systemDefault())
                            .toInstant()
            );

            Week week = Week.builder()
                    .weekId(new WeekId(team.getTeamId(), nextWeekNo))
                    .weekStaDate(weekStartDateAsDate)
                    .weekEndDate(weekEndDateAsDate)
                    .weekStaDayCd(weekStartDate.getDayOfWeek().name())
                    .weekEndDayCd(weekEndDate.getDayOfWeek().name())
                    .vacationYn("N")
                    .chkId("system")
                    .chkDate(new Date())
                    .build();

            weekRepository.save(week);
            log.info("# 주차 생성 완료 → 팀 :: {}, 주차 :: {}", team.getTeamId(), nextWeekNo);
        }
    }

    private int getNextWeekNumber(String teamId) {
        return weekRepository.findMaxWeekByTeamId(teamId).orElse(0) + 1;
    }

    private LocalDate getWeekStartDate(String startDayCd) {
        DayOfWeek startDay = DayOfWeek.valueOf(startDayCd);
        LocalDate today = LocalDate.now();
        return today.with(startDay);
    }
}
