package com.spring.backend.controller;

import com.spring.backend.model.WeekMember;
import com.spring.backend.model.WeekMemberId;
import com.spring.backend.repository.WeekMemberRepository;
import com.spring.backend.service.WeekMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/weekmember")
@RequiredArgsConstructor
public class WeekMemberController {

    private final WeekMemberService weekMemberService;

    @GetMapping
    public List<WeekMember> findAll() {
        return weekMemberService.findAll();
    }

    @GetMapping("/{teamId}/{memberId}/{week}")
    public ResponseEntity<WeekMember> findById(@PathVariable String teamId,
                                               @PathVariable String memberId,
                                               @PathVariable int week) {
        WeekMemberId id = new WeekMemberId(teamId, memberId, week);
        return weekMemberService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<WeekMember> save(@RequestBody WeekMember weekMember) {
        WeekMember savedWeekMember = weekMemberService.save(weekMember);
        return ResponseEntity.ok(savedWeekMember);
    }

    @PutMapping("/{teamId}/{memberId}/{week}")
    public ResponseEntity<WeekMember> update(@PathVariable String teamId,
                                             @PathVariable String memberId,
                                             @PathVariable int week,
                                             @RequestBody WeekMember weekMember) {
        WeekMemberId id = new WeekMemberId(teamId, memberId, week);
        return weekMemberService.findById(id)
                .map(isExistingWeekMember -> {
                    isExistingWeekMember.setVacationYn(weekMember.getVacationYn());
                    isExistingWeekMember.setGoalRegYn(weekMember.getGoalRegYn());
                    isExistingWeekMember.setFeedbackRegYn(weekMember.getFeedbackRegYn());
                    isExistingWeekMember.setTotAchieveRate(weekMember.getTotAchieveRate());
                    isExistingWeekMember.setComment(weekMember.getComment());
                    isExistingWeekMember.setMainGoalUnmet(weekMember.getMainGoalUnmet());
                    isExistingWeekMember.setChkId(weekMember.getChkId());
                    isExistingWeekMember.setChkDate(weekMember.getChkDate());

                    WeekMember savedWeekMember = weekMemberService.save(isExistingWeekMember);
                    return ResponseEntity.ok(savedWeekMember);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{teamId}/{memberId}/{week}")
    public ResponseEntity<Void> delete(@PathVariable String teamId,
                                       @PathVariable String memberId,
                                       @PathVariable int week) {
        WeekMemberId id = new WeekMemberId(teamId, memberId, week);
        weekMemberService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
