package com.fiap.lanchonete.dataprovider.pagamento.consumer;

import com.fiap.lanchonete.commons.type.StatusPagamento;
import com.fiap.lanchonete.controller.dto.PagamentoResponseDTO;
import com.fiap.lanchonete.dataprovider.pagamento.consumer.dto.QrCodeDTO;
import com.fiap.lanchonete.dataprovider.database.pedido.PedidoDataProvider;
import com.fiap.lanchonete.services.PedidoService;
import com.fiap.lanchonete.services.gateways.PagamentoConsumerGateway;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class PagamentoConsumer implements PagamentoConsumerGateway {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoDataProvider pedidoDataProvider;

    @Autowired
    private Gson gson;

    @RabbitListener(queues = {"${queues.qr_code_gerado}"})
    public void receiveQrCodeGerado(@Payload String message){
        HashMap<String, String> mensagem = gson.fromJson(message, HashMap.class);
        var qrCodeDTO = fromQrCodeMessage(mensagem);
        var pedido = pedidoDataProvider.findById(UUID.fromString(qrCodeDTO.getOrderIdentifier()));
        pedido.setQrCodePagamento(qrCodeDTO.getQrCode());
        pedidoDataProvider.save(pedido);
    }

    @RabbitListener(queues = {"${queues.pagamento_concluido}"})
    public void receivePagamentoConcluido(@Payload String message){
        HashMap<String, String> mensagem = gson.fromJson(message, HashMap.class);
        var pagamentoResponseDTO = fromPagamentoMessage(mensagem);
        pedidoService.validaPagamento(pagamentoResponseDTO);
    }

    private static PagamentoResponseDTO fromPagamentoMessage(Map message){
        return PagamentoResponseDTO.builder()
                .idPedido(UUID.fromString(message.get("orderIdentifier").toString()))
                .status(StatusPagamento.valueOf(message.get("status").toString()))
                .build();
    }

    private static QrCodeDTO fromQrCodeMessage(Map message){
        return QrCodeDTO.builder()
                .orderIdentifier(message.get("orderIdentifier").toString())
                .qrCode((String.valueOf(message.get("qrCode")).getBytes(StandardCharsets.UTF_8)))
                .build();
    }
}
