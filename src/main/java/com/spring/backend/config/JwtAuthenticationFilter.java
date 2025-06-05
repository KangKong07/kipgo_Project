package com.spring.backend.config;

import com.spring.backend.common.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("### JwtAuthenticationFilter: doFilterInternal called for request: " + request.getRequestURI()
                + " with method: " + request.getMethod());

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // "Bearer " 제거

            if (token == null || !jwtUtil.validateToken(token)) {   // ** 토큰이 없거나 유효하지 않은 경우
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);     // 401
                response.setContentType("application/json");
                response.getWriter().write("{\"message\":\"토큰이 유효하지 않거나 만료되었습니다.\"}");
                return;
            }
            else {  // ** 토큰이 유효한 경우
                String memberId = jwtUtil.getMemberIdFromToken(token);

                // * Spring Security 인증 정보 설정
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(memberId, null, null); // 권한 없음
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
