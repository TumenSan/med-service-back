package com.example.demo.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {
    private final RabbitTemplate rabbitTemplate;

    public ProducerController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    // curl -X POST http://localhost:8080/send -H "Content-Type: text/plain" -d "Привет, RabbitMQ!"
    @PostMapping("/send")
    public String sendMessage(@RequestBody String message) {
        rabbitTemplate.convertAndSend("medical_queue", message);
        return "Сообщение отправлено: " + message;
    }
}
