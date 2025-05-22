package com.example.demo.dto;

import java.time.LocalDateTime;

public class ChatMessageDto {
    private String senderName;
    private String senderIp;
    private String content;
    private LocalDateTime timestamp;

    // Конструкторы, геттеры и сеттеры
    public ChatMessageDto(String senderName, String content, LocalDateTime timestamp) {
        this.senderName = senderName;
        this.content = content;
        this.timestamp = timestamp;
    }

    public ChatMessageDto(String senderName, String senderIp, String content, LocalDateTime timestamp) {
        this.senderName = senderName;
        this.senderIp = senderIp;
        this.content = content;
        this.timestamp = timestamp;
    }

    // Геттеры и сеттеры
    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderIp() {
        return senderIp;
    }

    public void setSenderIp(String senderIp) {
        this.senderIp = senderIp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
