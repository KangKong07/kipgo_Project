package com.spring.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.backend.dto.response.ApiResponse;
import com.spring.backend.dto.response.WeekGoalInfoDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class MyWeekGoalDeleteTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("주간 목표 삭제 - 특정 목표 삭제 후 결과 확인")
    void deleteMyWeekGoal_shouldSucceed() throws Exception {
        // given
        int week = 117;
        int goalno = 2;
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("teamId", "kipgo216");
        session.setAttribute("memberId", "rudgns3456");

        // when
        String responseBody = mockMvc.perform(delete("/api/my-weekly/goal/delete/{week}/{goalno}", week, goalno)
                        .session(session)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // then
        ApiResponse<?> response = objectMapper.readValue(responseBody, ApiResponse.class);
        assertThat(response.getStatus()).isEqualTo("success");

        System.out.println("삭제 성공 메시지: " + response.getMessage());
        System.out.println("삭제 후 남은 목표: " + response.getData());
    }
}
