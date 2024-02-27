package com.fiap.lanchonete.dataprovider.producer;

import com.fiap.lanchonete.services.gateways.PagamentoGateway;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.amqp.core.Queue;

import java.util.UUID;

@Component
public class PagamentoProducer implements PagamentoGateway {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    @Override
    public void gerarQrCode(UUID id) {
        rabbitTemplate.convertAndSend(this.queue.getName(), id);
    }

    @Override
    public String consultarStatusPagamento(UUID idPedido) {
        return null;
    }
}
