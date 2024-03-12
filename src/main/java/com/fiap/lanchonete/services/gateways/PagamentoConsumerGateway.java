package com.fiap.lanchonete.services.gateways;

public interface PagamentoConsumerGateway {

    void receiveQrCodeGerado(String message);
    void receivePagamentoConcluido(String message);

}
