package com.spring.backend.service;

import com.spring.backend.common.exception.LoginFailedException;
import com.spring.backend.common.exception.UserNotFoundException;
import com.spring.backend.common.response.ApiResponse;
import com.spring.backend.common.util.JwtUtil;
import com.spring.backend.dto.projection.LoginTeamInfoProjection;
import com.spring.backend.dto.request.LoginRequestDto;
import com.spring.backend.dto.request.RegisterRequestDto;
import com.spring.backend.dto.response.LoginResponse;
import com.spring.backend.dto.response.LogoutResponse;
import com.spring.backend.model.Member;
import com.spring.backend.repository.LoginRepository;
import com.spring.backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final LoginRepository loginRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    /**
     * 로그인
     * @param request 로그인 요청 정보
     * @return 로그인 응답 정보 (JWT 토큰 포함)
     */
    public ApiResponse<LoginResponse> login(LoginRequestDto request) {

        // 1. 회원ID로 회원 정보 조회(+존재여부체크)
        Member member = memberRepository.findByMemberId(request.getMemberId())
                .orElseThrow(() -> new UserNotFoundException("존재하지 않는 회원입니다."));

        // 2. 비밀번호 일치 여부 확인
        if (!passwordEncoder.matches(request.getMemberPwd(), member.getMemberPwd())) {
            throw new LoginFailedException("비밀번호가 일치하지 않습니다.");
        }

        // 3. 탈퇴 여부 확인
        if ("Y".equalsIgnoreCase(member.getDeleteYn())) {
            throw new LoginFailedException("현재 존재하지 않는 회원입니다.");    // 탈퇴한 회원입니다.
        }

        // 4. JWT 토큰 생성
        String token = jwtUtil.generateToken(member);

        // 5. 팀정보 추가조회
        LoginTeamInfoProjection mainTeamInfo = loginRepository.findLoginTeamInfo(member.getMainTeamId(), member.getMemberId());
        System.out.println("### mainTeamInfo :: " + mainTeamInfo);

        LoginResponse response = new LoginResponse(token, member, mainTeamInfo);
        System.out.println("### login ok member :: " + member);

        return ApiResponse.ok(response);
    }


    /**
     * 회원가입
     * @param reqMember 회원가입 요청 정보
     */
    public void registerMember(RegisterRequestDto reqMember) {

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

    /**
     * 로그아웃
     * @param authHeader 현재 Token 저장 정보
     */
    public ApiResponse<LogoutResponse> logout(String authHeader) {

        // 1. 기존 Token 정보 추출
        String token = authHeader.replace("Bearer ", "");

        // 2. 기존 Token 내, memberId, teamId 정보 추출하여 검증
        String memberId = jwtUtil.getMemberIdFromToken(token);
        String teamId = jwtUtil.getClaimFromToken(token, "teamId");

        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new UserNotFoundException("존재하지 않는 회원입니다."));

        System.out.println("memberId = " + memberId);
        System.out.println("token = " + token);

        // 3. 기존 토큰 정보 내 memberId, teamId 삭제 후, 신규 토큰 발급
        String guestToken = jwtUtil.issueGuestToken(token);

        // 4. 로그아웃 후, 신규 토큰 전달
        LogoutResponse response = new LogoutResponse(guestToken);
        System.out.println("### Logout Ok member :: " + memberId);  // 로그아웃 성공 여부 로그 기록

        return ApiResponse.ok(response);
    }
}
