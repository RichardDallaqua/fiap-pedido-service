package com.fiap.lanchonete.dataprovider.consumer;

import com.fiap.lanchonete.commons.type.StatusPagamento;
import com.fiap.lanchonete.controller.dto.PagamentoResponseDTO;
import com.fiap.lanchonete.dataprovider.consumer.dto.QrCodeDTO;
import com.fiap.lanchonete.dataprovider.database.pedido.PedidoDataProvider;
import com.fiap.lanchonete.services.PedidoService;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class PedidoConsumer {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoDataProvider pedidoDataProvider;

    @Autowired
    private Gson gson;

    @RabbitListener(queues = {"${queue02.qr_code_gerado}"})
    public void receiveQrCodeGerado(@Payload String message){
        HashMap<String, String> mensagem = gson.fromJson(message, HashMap.class);
        var qrCodeDTO = fromQrCodeMessage(mensagem);
        var pedido = pedidoDataProvider.findById(UUID.fromString(qrCodeDTO.getOrderIdentifier()));
        pedido.setQrCodePagamento(qrCodeDTO.getQrCode());
        pedidoDataProvider.save(pedido);
    }

    @RabbitListener(queues = {"${queue03.pagamento_concluido}"})
    public void receivePagamentoConcluido(@Payload String message){
        HashMap<String, String> mensagem = gson.fromJson(message, HashMap.class);
        var pagamentoResponseDTO = fromPagamentoMessage(mensagem);
        pedidoService.validaPagamento(pagamentoResponseDTO);
    }

    private static PagamentoResponseDTO fromPagamentoMessage(Map message){
        return PagamentoResponseDTO.builder()
                .idPedido(UUID.fromString(message.get("idPedido").toString()))
                .status(StatusPagamento.valueOf(message.get("status").toString()))
                .build();
    }

    private static QrCodeDTO fromQrCodeMessage(Map message){
        return QrCodeDTO.builder()
                .orderIdentifier(message.get("orderIdentifier").toString())
                .qrCode((byte[]) message.get("qrCode"))
                .build();
    }
}
