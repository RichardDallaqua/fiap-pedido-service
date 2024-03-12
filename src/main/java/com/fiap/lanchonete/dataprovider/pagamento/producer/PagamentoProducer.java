package com.fiap.lanchonete.dataprovider.pagamento.producer;

import com.fiap.lanchonete.dataprovider.pagamento.dto.OrderInfoDTO;
import com.fiap.lanchonete.dataprovider.pagamento.producer.dto.ProducaoDTO;
import com.fiap.lanchonete.dataprovider.pagamento.producer.dto.RealizaPagamentoDTO;
import com.fiap.lanchonete.services.gateways.PagamentoProducerGateway;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.amqp.core.Queue;

import java.util.HashMap;
import java.util.Map;

@Component
public class PagamentoProducer implements PagamentoProducerGateway {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    @Value("${queue01.gerar_qr_code}")
    private String gerarQrCodeQueue;

    @Value("${queue03.realiza_pagamento}")
    private String realizaPagamentoQueue;

    @Value("${queue04.pagamento_concluido}")
    private String pagamentoConcluidoQueue;

    @Override
    public void gerarQrCode(OrderInfoDTO order) {
        rabbitTemplate.convertAndSend(gerarQrCodeQueue, toOrderInfoDTOMessage(order));
    }

    public void realizaPagamento(RealizaPagamentoDTO realizaPagamentoDTO) {
        rabbitTemplate.convertAndSend(realizaPagamentoQueue, toRealizaPagamentoDTOMessage(realizaPagamentoDTO));
    }

    public void pagamentoConcluido(ProducaoDTO producaoDTO){
        rabbitTemplate.convertAndSend(pagamentoConcluidoQueue, toProducaoDTOMessage(producaoDTO));
    }

    public static String toProducaoDTOMessage(ProducaoDTO producaoDTO){
        Map message = new HashMap<String, String>();
        message.put("idPedido", producaoDTO.getIdPedido());
        message.put("status", producaoDTO.getStatus());
        return new Gson().toJson(message);
    }

    public static String toOrderInfoDTOMessage(OrderInfoDTO order){
        Map message = new HashMap<String, String>();
        message.put("title", order.getTitle());
        message.put("items", order.getItems());
        message.put("orderIdentifier", order.getOrderIdentifier());
        message.put("totalAmount", order.getTotalAmount());
        return new Gson().toJson(message);
    }

    public static String toRealizaPagamentoDTOMessage(RealizaPagamentoDTO realizaPagamentoDTO){
        Map message = new HashMap<String, String>();
        message.put("orderIdentifier", realizaPagamentoDTO.getOrderIdentifier());
        message.put("status", realizaPagamentoDTO.getStatus());
        return new Gson().toJson(message);
    }
}
