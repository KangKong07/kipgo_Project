package com.spring.backend.controller;

import com.spring.backend.model.Week;
import com.spring.backend.model.WeekId;
import com.spring.backend.model.WeekMember;
import com.spring.backend.repository.WeekRepository;
import com.spring.backend.service.WeekService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class WeekVacationTest {
    @Autowired
    private WeekService weekService;

    @Autowired
    private WeekRepository weekRepository;

    @Test
    void saveVacation_shouldUpdateWeekMemberAndDecreaseVacationLimit() {
        // given
        String teamId = "kipgo216";
        String memberId = "rudgns3456";
        int weekNumber = 118;

        Week week = weekRepository.findById(new WeekId(teamId, weekNumber))
                .orElseThrow(() -> new IllegalArgumentException("해당 주차 정보가 없습니다."));

        // when
        WeekMember updated = weekService.saveVacation(teamId, memberId, week);

        // then
        assertThat(updated).isNotNull();
        assertThat(updated.getWeekMemberId().getWeek()).isEqualTo(118);
        assertThat(updated.getVacationYn()).isEqualTo("Y");
        assertThat(updated.getGoalRegYn()).isEqualTo("Y");
        assertThat(updated.getTotAchieveRate()).isEqualTo(100);
        assertThat(updated.getChkId()).isEqualTo(memberId);

        // 출력 (확인용)
        System.out.println("✅ 휴가 저장 결과: ");
        System.out.println("   week: " + updated.getWeekMemberId().getWeek());
        System.out.println("   vacationYn: " + updated.getVacationYn());
        System.out.println("   achieveRate: " + updated.getTotAchieveRate());
        System.out.println("   chkId: " + updated.getChkId());
        System.out.println("   chkDate: " + updated.getChkDate());
    }
}