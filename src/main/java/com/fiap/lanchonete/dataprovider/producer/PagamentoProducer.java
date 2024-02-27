package com.fiap.lanchonete.dataprovider.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.amqp.core.Queue;

@Component
public class PagamentoProducer  {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    public void send() {
        rabbitTemplate.convertAndSend(this.queue.getName(), "teste");
    }

}
