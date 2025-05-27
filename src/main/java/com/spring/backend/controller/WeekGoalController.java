package com.spring.backend.controller;

import com.spring.backend.model.WeekGoal;
import com.spring.backend.model.WeekGoalId;
import com.spring.backend.service.WeekGoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/weekgoal")
@RequiredArgsConstructor
public class WeekGoalController {

    private final WeekGoalService weekGoalService;

    @GetMapping
    public List<WeekGoal> getAllWeekGoals() {
        return weekGoalService.findAll();
    }

//    @GetMapping("/{teamId}/{memberId}/{week}/{goalNo}")
//    public ResponseEntity<WeekGoal> findById(@PathVariable String teamId,
//                                             @PathVariable String memberId,
//                                             @PathVariable int week,
//                                             @PathVariable int goalNo) {
//        WeekGoalId id = new WeekGoalId(teamId, memberId, week, goalNo);
//        return weekGoalService.findById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }

    @PostMapping
    public ResponseEntity<WeekGoal> createWeekGoal(@RequestBody WeekGoal weekGoal) {
        WeekGoal createdWeekGoal = weekGoalService.save(weekGoal);
        return ResponseEntity.ok(createdWeekGoal);
    }

//    @PutMapping("/{teamId}/{memberId}/{week}/{goalNo}")
//    public ResponseEntity<WeekGoal> updateWeekGoal(@PathVariable String teamId,
//                                                   @PathVariable String memberId,
//                                                   @PathVariable int week,
//                                                   @PathVariable int goalNo,
//                                                   @RequestBody WeekGoal weekGoal) {
//        WeekGoalId id = new WeekGoalId(teamId, memberId, week, goalNo);
//        return weekGoalService.findById(id)
//                .map(existingWeekGoal -> {
//                    WeekGoal updatedWeekGoal = WeekGoal.builder()
//                            .weekGoalId(existingWeekGoal.getWeekGoalId())
//                            .orderSeq(weekGoal.getOrderSeq())
//                            .mainGoalYn(weekGoal.getMainGoalYn())
//                            .achieveStatusCd(weekGoal.getAchieveStatusCd())
//                            .feedback(weekGoal.getFeedback())
//                            .chkId(weekGoal.getChkId())
//                            .chkDate(new Date())
//                            .build();
//
//                    WeekGoal savedWeekGoal = weekGoalService.save(updatedWeekGoal);
//                    return ResponseEntity.ok(savedWeekGoal);
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }

    @DeleteMapping("/{teamId}/{memberId}/{week}/{goalNo}")
    public ResponseEntity<Void> delete(@PathVariable String teamId,
                                       @PathVariable String memberId,
                                       @PathVariable int week,
                                       @PathVariable int goalNo) {
        WeekGoalId id = new WeekGoalId(teamId, memberId, week, goalNo);
        weekGoalService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
