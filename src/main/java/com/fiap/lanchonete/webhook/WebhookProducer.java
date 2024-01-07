package com.fiap.lanchonete.webhook;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebhookProducer {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public WebhookProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend("webhook", message);
    }
}
