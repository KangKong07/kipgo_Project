package com.spring.backend.controller;

import com.spring.backend.model.Week;
import com.spring.backend.model.WeekId;
import com.spring.backend.service.WeekService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/week")
@RequiredArgsConstructor
public class WeekController {

    private final WeekService weekService;

    @GetMapping
    public List<Week> findAll() {
        return weekService.findAll();
    }

    @GetMapping("/{teamId}/{week}")
    public ResponseEntity<Week> findById(@PathVariable String teamId,
                                         @PathVariable int week) {
        WeekId id = new WeekId(teamId, week);
        return weekService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Week> create(@RequestBody Week week) {
        Week savedWeek = weekService.save(week);
        return ResponseEntity.ok(savedWeek);
    }

    @PutMapping("/{teamId}/{week}")
    public ResponseEntity<Week> update(@PathVariable String teamId,
                                       @PathVariable int week,
                                       @RequestBody Week weekRequest) {
        WeekId id = new WeekId(teamId, week);
        return weekService.findById(id)
                .map(existingWeek -> {
                    existingWeek.setWeekStaDate(weekRequest.getWeekStaDate());
                    existingWeek.setWeekEndDate(weekRequest.getWeekEndDate());
                    existingWeek.setWeekStaDayCd(weekRequest.getWeekStaDayCd());
                    existingWeek.setWeekEndDayCd(weekRequest.getWeekEndDayCd());
                    existingWeek.setVacationYn(weekRequest.getVacationYn());
                    existingWeek.setChkId(weekRequest.getChkId());
                    existingWeek.setChkDate(weekRequest.getChkDate());

                    Week savedWeek = weekService.save(existingWeek);
                    return ResponseEntity.ok(savedWeek);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{teamId}/{week}")
    public ResponseEntity<Week> delete(@PathVariable String teamId, @PathVariable int week) {
        WeekId id = new WeekId(teamId, week);
        weekService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
