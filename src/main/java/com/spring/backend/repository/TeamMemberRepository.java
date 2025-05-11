package com.spring.backend.repository;

import com.spring.backend.model.TeamMember;
import com.spring.backend.model.TeamMemberId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface TeamMemberRepository extends JpaRepository<TeamMember, TeamMemberId> {

    @Query("""
        SELECT m 
          FROM TeamMember m
         WHERE m.teamMemberId.teamId = :teamId
           AND (m.joinStaDate IS NULL OR m.joinStaDate <= :weekStartDate)
           AND (m.joinEndDate IS NULL OR m.joinEndDate >= :weekStartDate)
    """)
    List<TeamMember> findValidMembersForWeek(@Param("teamId") String teamId,
                                             @Param("weekStartDate") Date weekStartDate);


}
