package com.spring.backend.service;

import com.spring.backend.dto.response.UserMainWeekInfoDto;
import com.spring.backend.repository.WeekRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserMainService {

    private final WeekRepository weekRepository;

    /*
     * 최근 주차 정보 조회
     */
    public UserMainWeekInfoDto getUserMainWeekInfo(String teamId, String memberId) {
        Date today = new Date(); // 현재 일자 기준

        return weekRepository.fintUserMainWeekInfo(teamId, memberId, today)
                .orElseThrow(() -> new NoSuchElementException("주차 정보가 존재하지 않습니다."));
    }
}
