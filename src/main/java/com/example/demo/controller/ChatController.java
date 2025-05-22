package com.example.demo.controller;

import com.example.demo.dto.ChatMessageDto;
import com.example.demo.dto.SendMessageRequestDto;
import com.example.demo.model.ChatMessage;
import com.example.demo.service.ChatService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    // Отправка сообщения другому серверу
    @PostMapping("/sendMessage")
    public ResponseEntity<String> sendMessage(@RequestBody SendMessageRequestDto request) {
        chatService.sendMessage(request.getTargetIp(), request.getSenderName(), request.getMessage());
        return ResponseEntity.ok("Сообщение отправлено");
    }

    // Получение сообщения от другого сервера метод других
    @GetMapping("/receive")
    public ResponseEntity<Void> receiveMessageViaGet(
            @RequestParam String senderName,
            @RequestParam String message,
            @RequestHeader(name = "X-Forwarded-For", required = false) String xForwardedFor,
            HttpServletRequest request) {

        String senderIp = (xForwardedFor != null) ? xForwardedFor : request.getRemoteAddr();

        ChatMessageDto dto = new ChatMessageDto(senderName, senderIp, message, LocalDateTime.now());
        chatService.receiveMessage(dto, senderIp);

        // chatService.receiveMessage(dto, "127.0.0.1"); // senderIp в параметре
        return ResponseEntity.ok().build();
    }

    // Получение сообщения от другого сервера
    @PostMapping("/receiveMessage")
    public ResponseEntity<Void> receiveMessage(
            @RequestBody ChatMessageDto dto,
            @RequestHeader(name = "X-Forwarded-For", required = false) String senderIp) {
        chatService.receiveMessage(dto, senderIp);
        return ResponseEntity.ok().build();
    }

    // Просмотр всех сообщений
    @GetMapping("/messages")
    public List<ChatMessageDto> getAllMessages() {
        List<ChatMessage> messages = chatService.getAllMessages();
        return messages.stream()
                .map(this::convertToDto)
                .toList();
    }

    // Фильтр по имени или IP
    @GetMapping("/messages/from")
    public List<ChatMessageDto> getMessagesFrom(@RequestParam String sender) {
        List<ChatMessage> messages = chatService.getMessagesBySender(sender);
        return messages.stream()
                .map(this::convertToDto)
                .toList();
    }

    // Преобразование ChatMessage в ChatMessageDto
    private ChatMessageDto convertToDto(ChatMessage message) {
        return new ChatMessageDto(
                message.getSenderName(),
                message.getContent(),
                message.getTimestamp()
        );
    }
}
