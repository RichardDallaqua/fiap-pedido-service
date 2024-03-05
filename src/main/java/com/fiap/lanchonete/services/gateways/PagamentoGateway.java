package com.fiap.lanchonete.services.gateways;

import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public interface PagamentoGateway {

    void gerarQrCode(final UUID id);

    void realizarPagamento(final UUID id);

}
