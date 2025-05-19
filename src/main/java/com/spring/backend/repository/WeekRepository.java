package com.spring.backend.repository;

import com.spring.backend.dto.response.UserMainWeekInfoDto;
import com.spring.backend.dto.response.WeekInfoDto;
import com.spring.backend.model.Week;
import com.spring.backend.model.WeekId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface WeekRepository  extends JpaRepository<Week, WeekId> {


    /**
     * 특정 팀의 가장 마지막 주차 값 조회 (최신 주차 번호)
     */
    @Query("SELECT MAX(w.weekId.week) FROM Week w WHERE w.weekId.teamId = :teamId")
    Optional<Integer> findMaxWeekByTeamId(@Param("teamId") String teamId);


    /**
     * 사용자 메인 - 조회일 기준 최근 주차 정보 조회
     */
    @Query(value = """
        SELECT W.WEEK AS week
               , W.WEEK_STA_DATE AS weekStaDate
               , W.WEEK_END_DATE AS weekEndDate
               , CASE WHEN :today BETWEEN W.WEEK_STA_DATE AND W.WEEK_END_DATE THEN 'Y' ELSE 'N' END AS isCurrent
               , COALESCE(PREV_WM.FEEDBACK_REG_YN, '') AS isFeedbackReg
          FROM WEEK W
     LEFT JOIN WEEK_MEMBER PREV_WM
            ON PREV_WM.TEAM_ID = W.TEAM_ID
           AND PREV_WM.WEEK = W.WEEK - 1
           AND PREV_WM.MEMBER_ID = :memberId
         WHERE W.TEAM_ID = :teamId
           AND (
                (:searchDate BETWEEN W.WEEK_STA_DATE AND W.WEEK_END_DATE)
                OR W.WEEK_END_DATE < :searchDate
              )
      ORDER BY CASE WHEN :searchDate BETWEEN W.WEEK_STA_DATE AND W.WEEK_END_DATE THEN 0 ELSE 1 END
               , W.WEEK_END_DATE DESC
     FETCH FIRST 1 ROWS ONLY
    """, nativeQuery = true)
    Optional<UserMainWeekInfoDto> fintUserMainWeekInfo(@Param("teamId") String teamId, @Param("memberId") String memberId, @Param("today") Date today, @Param("searchDate") Date searchDate);



    /**
     * 현재 참여중인 팀 내 주차 목록 조회
     */
    @Query(value = """
        SELECT W.WEEK AS week -- 주차
             , W.WEEK_STA_DATE AS weekStaDate -- 주차시작일자
             , W.WEEK_END_DATE AS weekEndDate -- 주차종료일자
             , W.VACATION_YN AS vacationYn -- 전체휴가여부
        FROM WEEK W
        INNER JOIN WEEK_MEMBER WM
        ON W.TEAM_ID = WM.TEAM_ID AND W.WEEK = WM.WEEK
        WHERE W.TEAM_ID = :teamId AND WM.MEMBER_ID = :memberId
        ORDER BY W.WEEK DESC
    """, nativeQuery = true)
    List<WeekInfoDto> findWeekListInfo(@Param("teamId") String teamId, @Param("memberId") String memberId);

}


