package com.spring.backend.controller;

import com.spring.backend.dto.response.WeekGoalInfoDto;
import com.spring.backend.repository.WeekGoalRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TeamWeekMemberControllerTest {

    @Autowired
    private WeekGoalRepository weekGoalRepository;

    @Test
    void getWeekGoals_shouldReturnGoalsForExistingMember() {
        // given
        String teamId = "kipgo216";
        String memberId = "rudgns3456";
        int week = 117;

        // when
        List<WeekGoalInfoDto> result = weekGoalRepository.getWeekGoals(teamId, memberId, week);

        // then
        assertThat(result).isNotEmpty(); // 데이터가 있어야 통과

        // 결과 출력
        System.out.println("=== 주간 목표 및 피드백 결과 ===");
        for (WeekGoalInfoDto dto : result) {
            System.out.printf("GoalNo: %d, Goal: %s, MainGoalYn: %s, Feedback: %s%n",
                    dto.getGoalNo(),
                    dto.getGoal(),
                    dto.getMainGoalYn(),
                    dto.getFeedback());
        }
    }
}