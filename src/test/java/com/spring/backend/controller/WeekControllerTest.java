package com.spring.backend.controller;

import com.spring.backend.BackendApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = BackendApplication.class)
@AutoConfigureMockMvc
class WeekControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getWeekListInfo_shouldReturnWeekInfoList() throws Exception {
        String teamId = "kipgo216";
        String memberId = "rudgns3456";

        // 👉 실제 DB 확인은 mockMvc로 검증 (service는 실제 로직 수행)
        MvcResult mvcResult = mockMvc.perform(get("/api/my-weekly/week-list")
                        .sessionAttr("teamId", teamId)
                        .sessionAttr("memberId", memberId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(3)) // 실제 DB 조건에 맞게 수정
                .andExpect(jsonPath("$[0].week").value(118))
                .andExpect(jsonPath("$[2].vacationYn").value("Y"))
                .andReturn();

        // 응답 본문 출력
        String jsonResponse = mvcResult.getResponse().getContentAsString();
        System.out.println("응답 Response : " + jsonResponse);
    }
}