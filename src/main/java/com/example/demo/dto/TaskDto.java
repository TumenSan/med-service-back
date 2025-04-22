package com.example.demo.dto;

import com.example.demo.model.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class TaskDto {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {
        private Long id;
        private Long userId;
        private Long modelId;
        private String taskType;
        private String parameters;
        private LocalDateTime createdAt;
        private Task.Status status;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private Long userId;
        private Long modelId;
        private String taskType;
        private String parameters;
        private Task.Status status;
        private LocalDateTime createdAt;
    }
}
