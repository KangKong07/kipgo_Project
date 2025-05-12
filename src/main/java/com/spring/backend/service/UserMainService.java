package com.spring.backend.service;

import com.spring.backend.dto.response.UserMainWeekInfoDto;
import com.spring.backend.model.TeamMember;
import com.spring.backend.model.TeamMemberId;
import com.spring.backend.repository.TeamMemberRepository;
import com.spring.backend.repository.WeekRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.NoSuchElementException;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserMainService {

    private final WeekRepository weekRepository;
    private final TeamMemberRepository teamMemberRepository;

    /*
     * 최근 주차 정보 조회
     */
    public UserMainWeekInfoDto getUserMainWeekInfo(String teamId, String memberId) {

        Date searchDate = new Date(); // 조회기준일 : 현재 일자(기본)
        Date today = new Date();

        // 1. 팀원 검증
        TeamMember searchTeamMember =  teamMemberRepository.findById(new TeamMemberId(teamId, memberId))
                .orElseThrow(() -> new NoSuchElementException("팀원 정보가 존재하지 않습니다."));

        // 2. 팀 참여일 체크
        if(searchTeamMember.getJoinEndDate() != null && searchTeamMember.getJoinEndDate().before(new Date())) {
            searchDate = searchTeamMember.getJoinEndDate();
        }

        log.info("# 팀원 참여일 체크 :: 팀 ID [{}] / 멤버 ID [{}] / 참여종료일자 [{}]",
                teamId, memberId, searchTeamMember.getJoinEndDate());

        // 3. 조회일자 기준 최근 주차 정보 1건 조회 후 리턴
        return weekRepository.fintUserMainWeekInfo(teamId, memberId, today, searchDate)
                .orElseThrow(() -> new NoSuchElementException("주차 정보가 존재하지 않습니다."));
    }
}
