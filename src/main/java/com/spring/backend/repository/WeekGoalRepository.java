package com.spring.backend.repository;

import com.spring.backend.dto.response.WeekGoalInfoDto;
import com.spring.backend.model.WeekGoal;
import com.spring.backend.model.WeekGoalId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WeekGoalRepository extends JpaRepository<WeekGoal, WeekGoalId> {

    /**
     * 지난 주차 자신의 목표 조회하기 (현재 Week - 1 데이터 가져오기)
     * @Param TeamID
     * @Param MemberID
     * @Param Week
     */
    @Query("SELECT w.weekGoalId.goalNo, w.goal, w.mainGoalYn " +
            "FROM WeekGoal w " +
            "WHERE w.weekGoalId.teamId = :teamId AND w.weekGoalId.memberId = :memberId AND w.weekGoalId.week = :week")
    List<Object[]> findGoalsByTeamMemberAndWeek(@Param("teamId") String teamId,
                                                @Param("memberId") String memberId,
                                                @Param("week") int week);

    /**
     * 현재 주차 작성된 목표 및 피드백 조회하기
     *
     */
    @Query(value = """
            SELECT WM.TEAM_ID AS teamId,
                   WM.WEEK AS week,
                   WM.MEMBER_ID AS memberId,
                   WM.GOAL_REG_YN AS goalRegYn,
                   WM.FEEDBACK_REG_YN AS feedbackRegYn,
                   WM.TOT_ACHIEVE_RATE AS totAchieveRate,
                   WM.VACATION_YN AS vacationYn,
                   WG.GOAL_NO AS goalNo,
                   WG.GOAL AS goal,
                   WG.MAIN_GOAL_YN AS mainGoalYn,
                   WG.FEEDBACK AS feedback
           FROM WEEK_MEMBER WM
           LEFT OUTER JOIN WEEK_GOAL WG
           ON WM.WEEK= WG.WEEK AND WM.MEMBER_ID = WG.MEMBER_ID AND WM.TEAM_ID = WG.TEAM_ID
           WHERE WM.TEAM_ID = :teamId AND WM.MEMBER_ID = :memberId AND WM.WEEK = :week
           ORDER BY WG.GOAL_NO
           """, nativeQuery = true)
    List<WeekGoalInfoDto> getWeekGoals(@Param("teamId") String teamId,
                                       @Param("memberId") String memberId,
                                       @Param("week") int week);

}
