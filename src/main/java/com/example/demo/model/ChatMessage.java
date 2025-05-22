package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String senderName;  // Имя отправителя
    private String senderIp;    // IP-адрес отправителя
    private String content;     // Текст сообщения
    private LocalDateTime timestamp; // Время отправки

    public ChatMessage() {}

    public ChatMessage(String senderName, String senderIp, String content) {
        this.senderName = senderName;
        this.senderIp = senderIp;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
