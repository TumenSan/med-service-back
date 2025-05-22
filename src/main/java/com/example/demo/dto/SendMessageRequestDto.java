package com.example.demo.dto;

import java.time.LocalDateTime;

public class SendMessageRequestDto {
    private String targetIp;
    private String senderName;
    private String message;

    public SendMessageRequestDto() {}

    public SendMessageRequestDto(String senderName, String message, LocalDateTime timestamp) {
        this.senderName = senderName;
        this.message = message;
    }
    // Геттеры и сеттеры
    public String getTargetIp() {
        return targetIp;
    }

    public void setTargetIp(String targetIp) {
        this.targetIp = targetIp;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
