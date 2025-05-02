package com.spring.backend.repository;

import com.spring.backend.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, String> {

    /**
     * 주간 시작 요일 코드가 주어진 요일과 같은 팀 목록 조회
     *  ex) "MON", "TUE", ...
     */
    List<Team> findByWeekStaDayCd(String weekStaDayCd);
}
