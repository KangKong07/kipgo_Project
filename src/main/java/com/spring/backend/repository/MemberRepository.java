package com.spring.backend.repository;

import com.spring.backend.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

    /**
     * 아이디로 존재 여부 확인
     * @param memberId 회원ID
     * @return 존재 여부
     */
    boolean existsByMemberId(String memberId);

    /**
     * 아이디로 회원 정보 조회
     * @param memberId 회원ID
     * @return 회원 정보
     */
    Optional<Member> findByMemberId(String memberId);

}
