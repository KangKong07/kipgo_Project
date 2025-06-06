package com.spring.backend.repository;

import com.spring.backend.dto.projection.LoginTeamInfoProjection;
import com.spring.backend.model.TeamMember;
import com.spring.backend.model.TeamMemberId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoginRepository extends JpaRepository<TeamMember, TeamMemberId> {

    @Query("SELECT " +
            " t.teamNm AS teamNm, " +
            " t.vacationLimit AS vacationLimit, " +
            " t.goalRegDeadline AS goalRegDeadline, " +
            " t.feedbackRegDeadline AS feedbackRegDeadline, " +
            " tm.nickname AS nickname, " +
            " tm.teamSetYn AS teamSetYn, " +
            " tm.memberVacationLimit AS memberVacationLimit " +
            "FROM TeamMember tm " +
            "JOIN Team t ON t.teamId = tm.teamMemberId.teamId " +
            "WHERE tm.teamMemberId.teamId = :teamId AND tm.teamMemberId.memberId = :memberId")
    LoginTeamInfoProjection findLoginTeamInfo(@Param("teamId") String teamId,
                                              @Param("memberId") String memberId);

}
