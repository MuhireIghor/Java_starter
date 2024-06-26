package com.ne.starter.dtos.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ApiResponse {
    private Boolean success;
    private String message;
    private Object data;

    public ApiResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ApiResponse(Boolean success, Object data) {
        this.success = success;
        this.data = data;
    }
}
