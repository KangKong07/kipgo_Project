package com.spring.backend.controller;

import com.spring.backend.model.Member;
import com.spring.backend.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public List<Member> findAll() {
        return memberService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMember(@PathVariable String id) {
        return memberService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Member> create(@RequestBody Member member) {
        Member savedMember = memberService.save(member);
        return ResponseEntity.ok(savedMember);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> update(@PathVariable String id, @RequestBody Member member) {
        return memberService.findById(id)
                .map(existingMember -> {
                    Member updatedMember = Member.builder()
                            .memberId(existingMember.getMemberId())
                            .name(member.getName())
                            .telNo(member.getTelNo())
                            .email(member.getEmail())
                            .memberPwd(member.getMemberPwd())
                            .deleteYn(member.getDeleteYn())
                            .joinDate(existingMember.getJoinDate())
                            .chkId(member.getChkId())
                            .chkDate(new Date())
                            .build();

                    Member savedMember = memberService.save(updatedMember);
                    return ResponseEntity.ok(savedMember);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Member> deleteMeber(@PathVariable String id) {
        memberService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
