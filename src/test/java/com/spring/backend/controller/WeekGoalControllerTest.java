package com.spring.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.backend.model.WeekGoal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class WeekGoalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final String teamId = "kipgo216";
    private final String memberId = "rudgns3456";
    private final int week = 119;

    @Test
    void saveMyWeekGoal_shouldReturnSuccessResponse() throws Exception {
        // given
        List<WeekGoal> goalList = List.of(
                new WeekGoal(teamId, memberId, week, 1, "운동하기", 1, "Y", "N", null, new Date()),
                new WeekGoal(teamId, memberId, week, 2, "책읽기", 2, "N", "N", null, new Date())
        );

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("teamId", teamId);
        session.setAttribute("memberId", memberId);

        // when & then
        mockMvc.perform(post("/api/my-weekly/goal/save/{week}", week)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(goalList))
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].goal").value("운동하기"));
    }

    @Test
    void saveMyWeekGoal_shouldFailIfNoMainGoal() throws Exception {
        // 메인 목표 없이 전송
        List<WeekGoal> goalList = List.of(
                new WeekGoal(teamId, memberId, week, 1, "운동하기", 1, "N", "N", null, new Date())
        );

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("teamId", teamId);
        session.setAttribute("memberId", memberId);

        mockMvc.perform(post("/api/my-weekly/goal/save/{week}", week)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(goalList))
                        .session(session)
                        .param("week", String.valueOf(week)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("fail"))
                .andExpect(jsonPath("$.message").value("메인 목표를 작성하지 않았습니다."));
    }
}