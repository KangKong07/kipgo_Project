package com.spring.backend.service;

import com.spring.backend.model.WeekGoal;
import com.spring.backend.model.WeekGoalId;
import com.spring.backend.repository.WeekGoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WeekGoalService {

    private final WeekGoalRepository weekGoalRepository;

    public List<WeekGoal> findAll() {
        return weekGoalRepository.findAll();
    }

    public Optional<WeekGoal> findById(WeekGoalId weekGoalId) {
        return weekGoalRepository.findById(weekGoalId);
    }

    public WeekGoal save(WeekGoal weekGoal) {
        return weekGoalRepository.save(weekGoal);
    }

    public void deleteById(WeekGoalId weekGoalId) {
        weekGoalRepository.deleteById(weekGoalId);
    }
}
