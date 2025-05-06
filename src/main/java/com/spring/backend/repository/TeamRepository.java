package com.spring.backend.repository;

import com.spring.backend.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TeamRepository extends JpaRepository<Team, String> {

    /**
     * 현재일 기준 운영중인 팀 중 주간 시작 요일코드에 해당하는 팀 목록조회
     *  ex) "MON", "TUE", ...
     */
    @Query("""
       SELECT t 
         FROM Team t
        WHERE t.weekStaDayCd = :dayCode
          AND (:today >= COALESCE(t.staDate, :today))
          AND (t.endDate IS NULL OR :today < t.endDate)
    """)
    List<Team> findByWeekStaDayCd(@Param("dayCode") String dayCode, @Param("today") LocalDate today);
}
