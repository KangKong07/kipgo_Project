package com.spring.backend.controller;

import com.spring.backend.model.Team;
import com.spring.backend.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
                .map(existingTeam -> {
                    Team updatedTeam = Team.builder()
                            .teamId(existingTeam.getTeamId())
                            .teamNm(team.getTeamNm())
                            .weekStaDayCd(team.getWeekStaDayCd())
                            .vacationLimit(team.getVacationLimit())
                            .pushUseYn(team.getPushUseYn())
                            .goalRegDeadline(team.getGoalRegDeadline())
                            .feedbackRegDeadline(team.getFeedbackRegDeadline())
                            .adminId(team.getAdminId())
                            .staDate(team.getStaDate())
                            .endDate(team.getEndDate())
                            .chkId(team.getChkId())
                            .chkDate(new Date())
                            .build();

                    Team savedTeam = teamService.save(updatedTeam);
                    return ResponseEntity.ok(savedTeam);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Team> delete(@PathVariable String id) {
        teamService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
