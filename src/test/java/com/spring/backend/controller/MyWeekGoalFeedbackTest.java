package com.spring.backend.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import com.spring.backend.common.util.ValidationUtils;
import com.spring.backend.dto.response.ApiResponse;
import com.spring.backend.dto.response.WeekGoalInfoDto;
import com.spring.backend.model.*;
import com.spring.backend.repository.*;
import com.spring.backend.service.WeekGoalService;
import com.spring.backend.service.WeekMemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;


@ExtendWith(MockitoExtension.class)
public class MyWeekGoalFeedbackTest {

    @InjectMocks
    private WeekGoalService weekFeedbackService;

    @Mock
    private TeamMemberRepository teamMemberRepository;

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private WeekMemberRepository weekMemberRepository;

    @Mock
    private WeekRepository weekRepository;

    @Mock
    private WeekGoalRepository weekGoalRepository;

    @Mock
    private WeekMemberService weekMemberService;

    @Mock
    private ValidationUtils validationUtils;

    @BeforeEach
    void setUp() {
        // 수동 생성
        weekFeedbackService = new WeekGoalService(
                weekGoalRepository,
                teamMemberRepository,
                teamRepository,
                weekRepository,
                weekMemberRepository,
                weekMemberService
        );

        // 필드 주입 (Spring 없이도 강제로 넣는 방법)
        ReflectionTestUtils.setField(weekFeedbackService, "validationUtils", validationUtils);
    }

    @Test
    void saveFeedback_mainGoalUnmetAndNoAchievement_shouldSaveSuccessfully() throws ParseException {


        // Given
        String teamId = "kipgo216";
        String memberId = "rudgns3456";
        int week = 119;

        TeamMember teamMember = new TeamMember();
        Team team = Team.builder().feedbackRegDeadline(3).build();
        WeekMember weekMember = WeekMember.builder().build();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date pastDate = sdf.parse("2024-05-01");

        Week weekEntity = Week.builder()
                .weekStaDate(pastDate)
                .build();

        WeekGoal goal1 = WeekGoal.builder()
                .weekGoalId(new WeekGoalId("kipgo216", "rudgns3456", 119, 1)) // 여기가 중요!
                .mainGoalYn("Y")
                .achieveStatusCd("S")
                .build();

        WeekGoal goal2 = WeekGoal.builder()
                .weekGoalId(new WeekGoalId("kipgo216", "rudgns3456", 119, 2)) // 여기가 중요!
                .mainGoalYn("N")
                .achieveStatusCd("N")
                .build();

        // 기존 목표 리스트 (예: 컨트롤러나 상위 계층에서 전달받은 것처럼)
//        WeekGoal goal1 = mock(WeekGoal.class);
//        when(goal1.getWeekGoalId().getGoalNo()).thenReturn(1);
//        when(goal1.getMainGoalYn()).thenReturn("Y");
//        when(goal1.getAchieveStatusCd()).thenReturn("S");
//
//        WeekGoal goal2 = mock(WeekGoal.class);
//        when(goal2.getWeekGoalId().getGoalNo()).thenReturn(2);
//        when(goal2.getMainGoalYn()).thenReturn("N");
//        when(goal2.getAchieveStatusCd()).thenReturn("N");

        List<WeekGoal> weekGoals = List.of(goal1, goal2);

        when(teamMemberRepository.findById(any())).thenReturn(Optional.of(teamMember));
        when(teamRepository.findById(any())).thenReturn(Optional.of(team));
        when(weekMemberRepository.findById(any())).thenReturn(Optional.of(weekMember));
        when(weekRepository.findById(any())).thenReturn(Optional.of(weekEntity));

        // ✅ 기한 지난 것으로 예외 발생 설정
        doThrow(new IllegalStateException("피드백 등록 기한이 지났습니다."))
                .when(validationUtils)
                .validateGoalDeadline(any(), anyInt());

        // When
        ApiResponse<List<WeekGoalInfoDto>> response = weekFeedbackService.saveFeedback(
                teamId, memberId, week, weekGoals
        );
        // Then
        assertTrue(response.getMessage().contains("피드백 등록 기한이 지났습니다."));
    }
}