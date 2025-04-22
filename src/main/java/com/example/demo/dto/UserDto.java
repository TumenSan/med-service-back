package com.example.demo.dto;

import com.example.demo.model.User;
import lombok.*;

import java.time.LocalDateTime;

public class UserDto {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {
        private String login;
        private String password;
        private User.Role role;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private String login;
        private User.Role role;
        private LocalDateTime createdAt;
    }
}
