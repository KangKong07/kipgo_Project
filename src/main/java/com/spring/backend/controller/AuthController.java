package com.spring.backend.controller;

import com.spring.backend.common.response.ApiResponse;
import com.spring.backend.dto.request.LoginRequest;
import com.spring.backend.dto.request.RegisterRequest;
import com.spring.backend.dto.response.LoginResponse;
import com.spring.backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    /**
     * 로그인
     * @param request 로그인 요청 정보
     * @return 로그인 응답 정보 (JWT 토큰 포함)
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }


    /**
     * 회원가입
     * @param reqMember 회원가입 요청 정보
     * @return ApiResponse 처리상태, 성공 또는 실패 메시지
     */
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> registerMember(@RequestBody RegisterRequest reqMember) {
        try {
            authService.registerMember(reqMember);    // 회원가입 처리 호출
            return ResponseEntity.ok(ApiResponse.success("회원가입 성공"));
        } catch (IllegalStateException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("회원가입 중 알 수 없는 오류 발생"));
        }
    }


}
