package com.spring.backend.service;

import com.spring.backend.common.util.JwtUtil;
import com.spring.backend.dto.request.LoginRequest;
import com.spring.backend.dto.response.LoginResponse;
import com.spring.backend.model.Member;
import com.spring.backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest request) {

        // 1. 회원ID로 회원 정보 조회(+존재여부체크)
        Member member = memberRepository.findByMemberId(request.getMemberId())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));

        // 2. 비밀번호 일치 여부 확인
        if (!passwordEncoder.matches(request.getMemberPwd(), member.getMemberPwd())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        // 3. 탈퇴 여부 확인
        if ("Y".equalsIgnoreCase(member.getDeleteYn())) {
            throw new RuntimeException("현재 존재하지 않는 회원입니다.");    // 탈퇴한 회원입니다.
        }

        // 4. JWT 토큰 생성
        String token = jwtUtil.generateToken(member.getMemberId());

        return new LoginResponse(token, member);
    }
}
