package com.spring.backend.repository;

import com.spring.backend.model.TeamMember;
import com.spring.backend.model.TeamMemberId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamMemberRepository extends JpaRepository<TeamMember, TeamMemberId> {

}
