package com.spring.backend.common.util;

import com.spring.backend.model.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
     * @param member 회원 정보
     * @return 생성된 JWT 토큰
     */
    public String generateToken(Member member) {
        long now = System.currentTimeMillis();
        long expiration = 1000 * 60 * 60 * 1; // 1시간(임시, 추후 refreshToken 적용 시 15분으로 변경예정)

        Map<String, Object> claims = new HashMap<>();
        claims.put("teamId", member.getMainTeamId());
        claims.put("name", member.getName());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(member.getMemberId()) // 회원id
                .setIssuedAt(new Date(now)) // 발급시각
                .setExpiration(new Date(now + expiration)) // 만료시각
                .signWith(key, SignatureAlgorithm.HS256) // 서명
                .compact(); // 생성
    }


    /**
     * JWT 토큰 유효성 검사
     * @param token JWT 토큰
     * @return 유효성 여부
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * JWT 토큰에서 회원 ID 추출
     * @param token JWT 토큰
     * @return 회원 ID
     */
    public String getMemberIdFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    /**
     * JWT 토큰에서 클레임 추출
     * @param token JWT 토큰
     * @param key 클레임 키
     * @return 클레임 값
     */
    public String getClaimFromToken(String token, String key) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(this.key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.get(key, String.class);
    }
}
