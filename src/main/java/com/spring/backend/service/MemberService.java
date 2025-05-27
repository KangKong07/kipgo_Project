package com.spring.backend.service;

import com.spring.backend.dto.request.RegisterRequest;
import com.spring.backend.model.Member;
import com.spring.backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입 처리
     * @param reqMember 회원가입 요청 정보
     */
    public void registerMember(RegisterRequest reqMember) {

        // 1. 회원ID 중복 체크
        boolean exists = memberRepository.existsByMemberId(reqMember.getMemberId());

        if (exists) {
            throw new IllegalStateException("이미 존재하는 사용자입니다.");
        }

        // 2. 비밀번호 유효성 검사
        // ** 비밀번호 8자리 이상, 대소문자와 특수문자가 섞였는지 체크
        if (!reqMember.getMemberPwd().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).{8,}$")) {
            throw new IllegalStateException("비밀번호는 8자 이상이며, 대문자, 소문자, 특수문자가 각각 하나 이상 포함되어야 합니다.");
        }

        // 3. 회원정보 저장
        Member member = Member.builder()
                .memberId(reqMember.getMemberId())
                .memberPwd(passwordEncoder.encode(reqMember.getMemberPwd()))
                .name(reqMember.getName())
                .telNo(reqMember.getTelNo())
                .email(reqMember.getEmail())
                .deleteYn("N")
                .joinDate(new Date())
                .chkId(reqMember.getMemberId())
                .chkDate(new Date())
                .mainTeamId("")
                .build();

        memberRepository.save(member);
    }

}
