package com.spring.backend.service;

import com.spring.backend.model.TeamMember;
import com.spring.backend.model.TeamMemberId;
import com.spring.backend.repository.TeamMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamMemberService {

    private final TeamMemberRepository teamMemberRepository;

    public List<TeamMember> findAll() {
        return teamMemberRepository.findAll();
    }

    public Optional<TeamMember> findById(TeamMemberId teamMemberId) {
        return teamMemberRepository.findById(teamMemberId);
    }

    public TeamMember save(TeamMember teamMember) {
        return teamMemberRepository.save(teamMember);
    }

    public void deleteById(TeamMemberId teamMemberId) {
        teamMemberRepository.deleteById(teamMemberId);
    }
}
