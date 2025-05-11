package com.spring.backend.controller;

import com.spring.backend.model.TeamMember;
import com.spring.backend.model.TeamMemberId;
import com.spring.backend.service.TeamMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/team-member")
@RequiredArgsConstructor
public class TeamMemberController {

    private final TeamMemberService teamMemberService;

    @GetMapping
    public List<TeamMember> findAll() {
        return teamMemberService.findAll();
    }

    @GetMapping("/{teamId}/{memberId}")
    public ResponseEntity<TeamMember> findById(@PathVariable String teamId,
                                               @PathVariable String memberId) {
        TeamMemberId id = new TeamMemberId(teamId, memberId);
        return teamMemberService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TeamMember> create(@RequestBody TeamMember teamMember) {
        TeamMember savedTeamMember = teamMemberService.save(teamMember);
        return ResponseEntity.ok(savedTeamMember);
    }

    @PutMapping("/{teamId}/{memberId}")
    public ResponseEntity<TeamMember> update(@PathVariable String teamId,
                                             @PathVariable String memberId,
                                             @RequestBody TeamMember teamMember) {
        TeamMemberId id = new TeamMemberId(teamId, memberId);
        return teamMemberService.findById(id)
                .map(existingTeamMember -> {
                    TeamMember updatedTeamMember = TeamMember.builder()
                            .teamMemberId(existingTeamMember.getTeamMemberId())
                            .nickName(teamMember.getNickName())
                            .joinStaDate(teamMember.getJoinStaDate())
                            .joinEndDate(teamMember.getJoinEndDate())
                            .profileFileId(teamMember.getProfileFileId())
                            .teamSetYn(teamMember.getTeamSetYn())
                            .chkId(teamMember.getChkId())
                            .chkDate(new Date())
                            .build();

                    TeamMember savedTeamMember = teamMemberService.save(updatedTeamMember);
                    return ResponseEntity.ok(savedTeamMember);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{teamId}/{memberId}")
    public ResponseEntity<Void> delete(@PathVariable String teamId,
                                       @PathVariable String memberId) {
        TeamMemberId id = new TeamMemberId(teamId, memberId);
        teamMemberService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
