package com.spring.backend.service;

import com.spring.backend.model.Team;
import com.spring.backend.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    public Optional<Team> findById(String id) {
        return teamRepository.findById(id);
    }

    public Team save(Team team) {
        return teamRepository.save(team);
    }

    public void deleteById(String id) {
        teamRepository.deleteById(id);
    }
}
