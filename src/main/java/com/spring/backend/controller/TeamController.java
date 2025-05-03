package com.spring.backend.controller;

import com.spring.backend.model.Team;
import com.spring.backend.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/team")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @GetMapping
    public List<Team> findAll() { return teamService.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Team> findById(@PathVariable String id) {
        return teamService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<Team> create(@RequestBody Team team) {
        Team savedTeam = teamService.save(team);
        return ResponseEntity.ok(savedTeam);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Team> update(@PathVariable String id, @RequestBody Team team) {
        return teamService.findById(id)
                .map(existingMember -> {
                    existingMember.setTeamId(team.getTeamId());
                    existingMember.setTeamNm(team.getTeamNm());
                    existingMember.setWeekStaDayCd(team.getWeekStaDayCd());
                    existingMember.setVacationLimit(team.getVacationLimit());
                    existingMember.setPushUseYn(team.getPushUseYn());
                    existingMember.setGoalRegDeadline(team.getGoalRegDeadline());
                    existingMember.setFeedbackRegDeadline(team.getFeedbackRegDeadline());
                    existingMember.setAdminId(team.getAdminId());
                    existingMember.setStaDate(team.getStaDate());
                    existingMember.setEndDate(team.getEndDate());
                    existingMember.setChkDate(team.getChkDate());

                    Team savedTeam = teamService.save(existingMember);
                    return ResponseEntity.ok(existingMember);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Team> delete(@PathVariable String id) {
        teamService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
