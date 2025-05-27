package com.spring.backend.repository;

import com.spring.backend.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

    /**
     * 사용자 아이디로 존재 여부 확인
     * @param memberId 회원ID
     * @return 존재 여부
     */
    boolean existsByMemberId(String memberId);

}
