package com.spring.backend.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(true, "요청 성공", data);
    }

    public static ApiResponse<?> success(String message) {
        return new ApiResponse<>(true, message, null);
    }
    public static ApiResponse<?> error(String message) {
        return new ApiResponse<>(false, message, null);
    }
}
