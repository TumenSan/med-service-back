package com.example.demo.service;

import com.example.demo.dto.ChatMessageDto;
import com.example.demo.dto.SendMessageRequestDto;
import com.example.demo.model.ChatMessage;
import com.example.demo.repository.ChatMessageRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ChatService {

    private ChatMessageRepository chatMessageRepository;
    private RestTemplate restTemplate;

    public ChatService(ChatMessageRepository chatMessageRepository, RestTemplate restTemplate) {
        this.chatMessageRepository = chatMessageRepository;
        this.restTemplate = restTemplate;
    }

    // Метод для формирования заголовков
    private static HttpHeaders createJsonHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }

    // Отправка сообщения на другой сервер
    public void sendMessage(String targetIp, String senderName, String message) {
        if ("localhost".equalsIgnoreCase(targetIp) || "127.0.0.1".equals(targetIp)) {
            ChatMessageDto dto = new ChatMessageDto(
                    senderName,
                    "127.0.0.1", // senderIp
                    message,
                    LocalDateTime.now()
            );
            receiveMessage(dto, "127.0.0.1"); // вызов напрямую
            return;
        }

        String url = "http://" + targetIp + ":8080/api/receiveMessage";

        // Создаем DTO с данными
        ChatMessageDto payloadDto = new ChatMessageDto(
                senderName,
                "127.0.0.1", // добавьте IP
                message,
                LocalDateTime.now()
        );

        HttpHeaders headers = createJsonHeaders();
        headers.set("X-Forwarded-For", "127.0.0.1");
        // Формируем запрос с заголовками и телом
        HttpEntity<ChatMessageDto> requestEntity = new HttpEntity<>(payloadDto, createJsonHeaders());

        // Отправляем запрос
        try {
            restTemplate.postForEntity(url, requestEntity, String.class);
        } catch (Exception e) {
            throw new RuntimeException("Не удалось отправить сообщение на " + targetIp);
        }
    }

    // Получение сообщения от другого сервера
    public void receiveMessage(ChatMessageDto dto, String senderIp) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSenderName(dto.getSenderName());
        chatMessage.setSenderIp(senderIp);
        chatMessage.setContent(dto.getContent());
        chatMessage.setTimestamp(dto.getTimestamp());

        chatMessageRepository.save(chatMessage);
    }

    // Получение всех сообщений
    public List<ChatMessage> getAllMessages() {
        return chatMessageRepository.findAll();
    }

    // Получение сообщений по имени или IP
    public List<ChatMessage> getMessagesBySender(String senderNameOrIp) {
        List<ChatMessage> byName = chatMessageRepository.findBySenderName(senderNameOrIp);
        List<ChatMessage> byIp = chatMessageRepository.findBySenderIp(senderNameOrIp);
        List<ChatMessage> result = new ArrayList<>();
        result.addAll(byName);
        result.addAll(byIp);
        return result;
    }
}
