package com.example.demo.controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ResultConsumerController {
    @RabbitListener(queues = "medical_results")
    public void receiveResult(String result) {
        System.out.println("The result was obtained: " + result);
        // Здесь можно сохранить в БД или отправить уведомление
    }
}
