package com.spring.backend.service;

import com.spring.backend.model.Week;
import com.spring.backend.model.WeekId;
import com.spring.backend.repository.WeekRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WeekService {

    private final WeekRepository weekRepository;

    public List<Week> findAll() {
        return weekRepository.findAll();
    }

    public Optional<Week> findById(WeekId weekId) {
        return weekRepository.findById(weekId);
    }

    public Week save(Week week) {
        return weekRepository.save(week);
    }

    public void deleteById(WeekId weekId) {
        weekRepository.deleteById(weekId);
    }
}
