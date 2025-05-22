package com.example.demo.dto;

import java.time.LocalDateTime;

public class SendMessageRequestDto {
    private String ip;
    private String name;
    private String message;

    public SendMessageRequestDto() {}

    public SendMessageRequestDto(String senderName, String message, LocalDateTime timestamp) {
        this.name = senderName;
        this.message = message;
    }
    // Геттеры и сеттеры
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
