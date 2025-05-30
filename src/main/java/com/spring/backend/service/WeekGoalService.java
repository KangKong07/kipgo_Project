package com.spring.backend.service;

import com.spring.backend.common.util.ValidationUtils;
import com.spring.backend.dto.response.ApiResponse;
import com.spring.backend.dto.response.WeekGoalInfoDto;
import com.spring.backend.model.*;
import com.spring.backend.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeekGoalService {

    private final WeekGoalRepository weekGoalRepository;
    private final TeamMemberRepository teamMemberRepository;
    private final TeamRepository teamRepository;
    private final WeekRepository weekRepository;
    private final WeekMemberRepository weekMemberRepository;
    private final WeekMemberService weekMemberService;

    @Autowired
    ValidationUtils validationUtils;

    public List<WeekGoal> findAll() {
        return weekGoalRepository.findAll();
    }

//    public Optional<WeekGoal> findById(WeekGoalId weekGoalId) {
//        return weekGoalRepository.findById(weekGoalId);
//    }

    public WeekGoal save(WeekGoal weekGoal) {
        return weekGoalRepository.save(weekGoal);
    }

    public void deleteById(WeekGoalId weekGoalId) {
        weekGoalRepository.deleteById(weekGoalId);
    }

    /**
     * 지난 주차 자신의 목표 조회하기 (현재 Week - 1 데이터 가져오기)
     * @Param TeamID
     * @Param MemberID
     * @Param Week
     */
    public List<Object[]> findGoalsByTeamMemberAndWeek(WeekGoalId weekGoalId) {
        return weekGoalRepository.findGoalsByTeamMemberAndWeek(weekGoalId.getTeamId(), weekGoalId.getMemberId(), weekGoalId.getWeek());
    }

    /**
     * 현재 주차에 작성된 나의 목표 리스트 가져오기
     * @Param TeamID
     * @Param MemberID
     * @Param Week
     */
    public List<WeekGoalInfoDto> getMyWeekGoalInfo(String teamId, String memberId, int week) {
        List<WeekGoalInfoDto> weekGoalInfo = weekGoalRepository.getWeekGoals(teamId, memberId, week);

        if (weekGoalInfo.isEmpty()) {
            throw new NoSuchElementException("해당 주차의 목표 정보가 없습니다.");
        }

        // 첫 번째 데이터 기준으로 휴가 여부 판단
        // 현재 주차와 지난주차에 대한 구분 여부 검토 필요
        String vacationYn = weekGoalInfo.get(0).getVacationYn();
        if ("Y".equals(vacationYn)) {
            throw new IllegalArgumentException(week + "주차는 휴가로 진행된 목표가 없습니다.");
        }

        return weekGoalInfo;
    }

    /**
     * 팀원별 목표 및 피드백 조회
     * @Param TeamID
     * @Param MemberID
     * @Param Week
     */
    public List<WeekGoalInfoDto> getTeamWeekGoalInfo(String teamId, String teamMemberId, int week) {
        List<WeekGoalInfoDto> teamMemberweekGoalsInfo = weekGoalRepository.getWeekGoals(teamId, teamMemberId, week);

        if (teamMemberweekGoalsInfo.isEmpty()) {
            throw new NoSuchElementException(
                    String.format("해당 주차의 목표 정보가 없습니다. (teamId: %s, memberId: %s, week: %d)",
                            teamId, teamMemberId, week));
        }

        return teamMemberweekGoalsInfo;
    }

    /**
     * 나의 목표 저장
     * @Param TeamID
     * @Param MemberID
     * @Param Week
     * @Param WeekGoal
     */
    @Transactional
    public ApiResponse<List<WeekGoalInfoDto>> saveMyWeekGoal(String teamId, String memberId, int week, List<WeekGoal> weekGoalList) {

        try {
            // 1. 팀원 검증
            TeamMember searchTeamMember = teamMemberRepository.findById(new TeamMemberId(teamId, memberId))
                    .orElseThrow(() -> new NoSuchElementException("팀원 정보가 존재하지 않습니다."));

            // 2. 팀 정보 조회
            Team team = teamRepository.findById(teamId)
                    .orElseThrow(() -> new NoSuchElementException("팀 정보를 찾을 수 없습니다."));

            // 3. 목표 작성 기한 검증 --> Optional 객체 확인 필요
            Week weekEntity = weekRepository.findById(new WeekId(teamId, week))
                    .orElseThrow(() -> new NoSuchElementException("해당 주차 정보를 찾을 수 없습니다."));
            validationUtils.validateGoalDeadline(weekEntity.getWeekStaDate(), team.getGoalRegDeadline());


            // 4. 메인 목표 작성 여부
            boolean isMainGoal = false;
            if(weekGoalList != null && weekGoalList.size() > 0) {
                for(WeekGoal weekGoal : weekGoalList) {
                    String mainGoalYn = weekGoal.getMainGoalYn();
                    if("Y".equals(mainGoalYn)) {
                        isMainGoal = true;
                        break;
                    }
                }
            }
            if(!isMainGoal) {
                throw new IllegalArgumentException("메인 목표를 작성하지 않았습니다.");
            }

            // WeekGoalList 저장
            weekGoalList.forEach(weekGoalRepository::save);

            // WeekMember 업데이트
            WeekMemberId weekMemberId = new WeekMemberId(teamId, memberId, week);
            weekMemberRepository.findById(weekMemberId)
                    .map(existingWeekMember -> weekMemberService.save(existingWeekMember.toBuilder()
                            .vacationYn("N")
                            .goalRegYn("Y")
                            .chkId(memberId)
                            .chkDate(new Date())
                            .build()))
                    .orElseThrow(() -> new NoSuchElementException("해당 주차의 WeekMember 정보를 찾을 수 없습니다."));

            List<WeekGoalInfoDto> goalInfoList = weekGoalRepository.getWeekGoals(teamId, memberId, week);
            if (goalInfoList == null || goalInfoList.isEmpty()) {
                return ApiResponse.failure("목표 저장은 되었으나 조회 결과가 없습니다.");
            }

            return ApiResponse.success("목표 저장 및 조회 성공", goalInfoList);

        } catch (NoSuchElementException | IllegalArgumentException e) {
            return ApiResponse.failure(e.getMessage());
        } catch (Exception e) {
            return ApiResponse.failure("주간 목표 저장 중 예기치 못한 오류가 발생했습니다." + e.getMessage());
        }

    }

    public ApiResponse<List<WeekGoalInfoDto>> deleteMyWeekGoal(String teamId, String memberId, int week, int goalno) {
        try {
            // 1. 팀원 검증
            TeamMember searchTeamMember = teamMemberRepository.findById(new TeamMemberId(teamId, memberId))
                    .orElseThrow(() -> new NoSuchElementException("팀원 정보가 존재하지 않습니다."));

            // 2. 팀 정보 조회
            Team team = teamRepository.findById(teamId)
                    .orElseThrow(() -> new NoSuchElementException("팀 정보를 찾을 수 없습니다."));

            // 3. 목표 작성 기한 검증
            Week weekEntity = weekRepository.findById(new WeekId(teamId, week))
                    .orElseThrow(() -> new NoSuchElementException("해당 주차 정보를 찾을 수 없습니다."));
            validationUtils.validateGoalDeadline(weekEntity.getWeekStaDate(), team.getGoalRegDeadline());

            // 4. 목표 번호 조회 검증
            weekGoalRepository.findById(new WeekGoalId(teamId, memberId, week, goalno))
                    .orElseThrow(() -> new NoSuchElementException("해당 주차의 목표를 찾을 수 없습니다."));

            weekGoalRepository.deleteById(new WeekGoalId(teamId, memberId, week, goalno));

            List<WeekGoalInfoDto> remainingGoals = weekGoalRepository.getWeekGoals(teamId, memberId, week);
            String message = remainingGoals.isEmpty()
                    ? "모든 목표가 삭제되어 현재 작성된 목표가 없습니다."
                    : "해당 목표가 삭제되었습니다.";
            return ApiResponse.success(message, remainingGoals);

        } catch (Exception e) {
            return ApiResponse.failure("주간 목표 삭제 중 예기치 못한 오류가 발생했습니다." + e.getMessage());
        }
    }

    /*
    * 주간 목표에 대한 피드백 저장 후, 피드백 바로 리턴
    */
    @Transactional
    public ApiResponse<List<WeekGoalInfoDto>> saveFeedback(String teamId, String memberId, int week, List<WeekGoal> weekGoalList) {
        try {
            // 1. 팀원 검증
            TeamMember searchTeamMember = teamMemberRepository.findById(new TeamMemberId(teamId, memberId))
                    .orElseThrow(() -> new NoSuchElementException("팀원 정보가 존재하지 않습니다."));

            // 2. 팀 정보 조회
            Team team = teamRepository.findById(teamId)
                    .orElseThrow(() -> new NoSuchElementException("팀 정보를 찾을 수 없습니다."));

            // 3. 이번 주차 참여 여부 검증
            WeekMemberId weekMemberId = new WeekMemberId(teamId, memberId, week);
            WeekMember weekMember = weekMemberRepository.findById(weekMemberId)
                    .orElseThrow(() -> new NoSuchElementException("해당 주차의 참여 정보를 찾을 수 없습니다."));

            // 4. 피드백 작성 기한 검증 - 주차 마지막날로 기한 검증
            Week weekEntity = weekRepository.findById(new WeekId(teamId, week))
                    .orElseThrow(() -> new NoSuchElementException("해당 주차 정보를 찾을 수 없습니다."));
            validationUtils.validateGoalDeadline(weekEntity.getWeekEndDate(), team.getFeedbackRegDeadline());

            // 4. 피드백 리스트 저장
            if(weekGoalList != null && !weekGoalList.isEmpty()) {
                weekGoalRepository.saveAll(weekGoalList);

                // 5. WeekMember 업데이트 진행 - 총달성률/메인목표달성/피드백작성여부
                // 5.1. 총 달성률 계산
                float totAchieveRate = getTotAchieveRate(teamId, memberId, week, weekGoalList);

                // 5.2. 메인 목표 달성 여부
                String mainGoalUnmet = isMainGoalUnmet(teamId, memberId, week, weekGoalList) ? "Y" : "N";

                weekMemberService.save(weekMember.toBuilder()
                        .feedbackRegYn("Y")
                        .totAchieveRate(totAchieveRate)
                        .mainGoalUnmet(mainGoalUnmet)
                        .chkId(memberId)
                        .chkDate(new Date())
                        .build());

                // 6. 작성된 피드백 정보 포함하여 WeekGoalDto 리턴
                List<WeekGoalInfoDto> goalInfoList = weekGoalRepository.getWeekGoals(teamId, memberId, week);
                return ApiResponse.success("목표 저장 및 조회 성공", goalInfoList);
            } else {
                throw new IllegalArgumentException("현재 주차에 작성된 피드백 정보를 찾을 수 없습니다.");
            }

        } catch (Exception e) {
            log.error("피드백 저장 중 오류 발생", e);
            return ApiResponse.failure("주간 피드백 저장 중 오류가 발생했습니다. 관리자에게 문의해주세요.");
        }
    }

    private float getTotAchieveRate(String teamId, String memberId, int week ,List<WeekGoal> weekGoalList) {

        int weekGoalCount = weekGoalList.size();    // 전체 목표 수
        int weekGoalAchieveCount = weekGoalRepository.getWeekGoalAchieveCnt(teamId, memberId, week);    // 달성한 목표 수

        if (weekGoalCount == 0) {
            return 0;
        }

        return ((float) weekGoalAchieveCount / weekGoalCount) * 100;
    }

    /**
     * 메인목표 달성여부 체크 로직
     */
    private boolean isMainGoalUnmet(String teamId, String memberId, int week, List<WeekGoal> weekGoalList) {
        boolean mainGoalUnmet = false;

        if(weekGoalList != null && !weekGoalList.isEmpty()) {
            for (WeekGoal weekGoal : weekGoalList) {
                if("Y".equals(weekGoal.getMainGoalYn()) && "S".equals(weekGoal.getAchieveStatusCd())) {
                    mainGoalUnmet = true;
                    break;
                }
            }
        }

        return mainGoalUnmet;
    }


    // 휴가일수 검증 로직
//    private void validateGoalDeadline(Date startDate, int deadlineDays) {
//        LocalDate deadline = startDate.toInstant()
//                .atZone(ZoneId.systemDefault())
//                .toLocalDate()
//                .plusDays(deadlineDays);
//
//        Date deadlineDate = Date.from(deadline.atStartOfDay(ZoneId.systemDefault()).toInstant());
//        Date now = new Date();
//
//        if (now.after(deadlineDate)) {
//            throw new IllegalArgumentException("목표 등록 기한이 지났습니다. (기한: " + deadlineDate + "까지)");
//        }
//    }
}
