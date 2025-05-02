package com.spring.backend.repository;

import com.spring.backend.model.Week;
import com.spring.backend.model.WeekId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WeekRepository  extends JpaRepository<Week, WeekId> {

    /**
     * 특정 팀의 가장 마지막 주차 값 조회 (최신 주차 번호)
     */
    @Query("SELECT MAX(w.weekId.week) FROM Week w WHERE w.weekId.teamId = :teamId")
    Optional<Integer> findMaxWeekByTeamId(@Param("teamId") String teamId);

}


