package com.spring.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.backend.dto.response.WeekGoalInfoDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class MyWeekGoalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("현재 주차 목표 조회 - 정상 응답")
    void getMyWeekGoal_shouldReturnSuccess() throws Exception {
        // given
        int week = 118;
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("teamId", "kipgo216");
        session.setAttribute("memberId", "rudgns3456");

        // when
        String responseBody = mockMvc.perform(get("/api/my-weekly/goal-feedback/{week}", week)
                        .session(session)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // then
        List<?> goalList = objectMapper.readValue(responseBody, List.class);
        assertThat(goalList).isNotEmpty();

        System.out.println("=== 조회된 목표 수: " + goalList.size() + "건 ===");
        goalList.forEach(goal -> System.out.println(goal));
    }
}