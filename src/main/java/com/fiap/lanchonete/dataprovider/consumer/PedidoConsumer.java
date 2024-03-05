package com.fiap.lanchonete.dataprovider.consumer;

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
    public void receive(@Payload String message){
        HashMap<String, String> mensagem = gson.fromJson(message, HashMap.class);
        var qrCodeDTO = fromMessage(mensagem);
        var pedido = pedidoDataProvider.findById(UUID.fromString(qrCodeDTO.getOrderIdentifier()));
        pedido.setQrCodePagamento(qrCodeDTO.getQrCode());
        pedidoDataProvider.save(pedido);
    }

    public static QrCodeDTO fromMessage(Map message){
        QrCodeDTO.builder()
                .orderIdentifier(message.get("orderIdentifier").toString())
                .qrCode((byte[]) message.get("qrCode"))
                .build();
    }
}
