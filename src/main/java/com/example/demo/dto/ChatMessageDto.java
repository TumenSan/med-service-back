package com.example.demo.dto;

import java.time.LocalDateTime;

public class ChatMessageDto {
    private String name;
    private String ip;
    private String message;
    private LocalDateTime timestamp;

    // Конструкторы, геттеры и сеттеры
    public ChatMessageDto() {}

    public ChatMessageDto(String senderName, String content, LocalDateTime timestamp) {
        this.name = senderName;
        this.message = content;
        this.timestamp = timestamp;
    }

    public ChatMessageDto(String senderName, String senderIp, String content, LocalDateTime timestamp) {
        this.name = senderName;
        this.ip = senderIp;
        this.message = content;
        this.timestamp = timestamp;
    }

    // Геттеры и сеттеры
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
