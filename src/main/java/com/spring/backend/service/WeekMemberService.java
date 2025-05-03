package com.spring.backend.service;

import com.spring.backend.model.WeekMember;
import com.spring.backend.model.WeekMemberId;
import com.spring.backend.repository.WeekMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WeekMemberService {

    private final WeekMemberRepository weekMemberRepository;

    public List<WeekMember> findAll() {
        return weekMemberRepository.findAll();
    }

    public Optional<WeekMember> findById(WeekMemberId weekMemberId) {
        return weekMemberRepository.findById(weekMemberId);
    }

    public WeekMember save(WeekMember weekMember) {
        return weekMemberRepository.save(weekMember);
    }

    public void deleteById(WeekMemberId weekMemberId) {
        weekMemberRepository.deleteById(weekMemberId);
    }
}
