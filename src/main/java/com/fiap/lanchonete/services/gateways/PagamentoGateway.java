package com.fiap.lanchonete.services.gateways;

import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public interface PagamentoGateway {

    byte[] gerarQrCode(final UUID id);

    String consultarStatusPagamento(final UUID idPedido);

}
