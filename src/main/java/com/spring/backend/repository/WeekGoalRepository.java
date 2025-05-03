package com.spring.backend.repository;

import com.spring.backend.model.WeekGoal;
import com.spring.backend.model.WeekGoalId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeekGoalRepository extends JpaRepository<WeekGoal, WeekGoalId> {
}
