package com.spring.backend.dto.response;

import lombok.Data;

@Data
public class LogoutResponse {
    private String accessToken;

    public LogoutResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
