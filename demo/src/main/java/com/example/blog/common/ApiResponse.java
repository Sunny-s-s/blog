package com.example.blog.common;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    private int code;
    private String message;
    private Object data;

    public static ApiResponse success(String message) {
        ApiResponse response = new ApiResponse();
        response.setCode(200);
        response.setMessage(message);
        return response;
    }

    public static ApiResponse success(String message, Object data) {
        ApiResponse response = success(message);
        response.setData(data);
        return response;
    }

    public static ApiResponse error(int code, String message) {
        ApiResponse response = new ApiResponse();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }
}