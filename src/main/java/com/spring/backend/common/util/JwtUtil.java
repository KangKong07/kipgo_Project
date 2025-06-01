package com.spring.backend.common.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key key;

    /**
     * JwtUtil 생성자
     * @param secret JWT 서명에 사용할 비밀 키
     */
    public JwtUtil(@Value("${jwt.secret}") String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    /**
     * JWT 토큰 생성
     * @param memberId 회원 ID
     * @return 생성된 JWT 토큰
     */
    public String generateToken(String memberId) {
        long now = System.currentTimeMillis();
        long expiration = 1000 * 60 * 60 * 24; // 24시간

        return Jwts.builder()
                .setSubject(memberId) // 회원id
                .setIssuedAt(new Date(now)) // 발급시각
                .setExpiration(new Date(now + expiration)) // 만료시각
                .signWith(key, SignatureAlgorithm.HS256) // 서명
                .compact(); // 생성
    }
}
