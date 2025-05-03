package com.spring.backend.repository;

import com.spring.backend.model.WeekMember;
import com.spring.backend.model.WeekMemberId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeekMemberRepository extends JpaRepository<WeekMember, WeekMemberId> {
}
