package com.fiap.lanchonete.services.gateways;

import java.util.List;
import java.util.UUID;

import com.fiap.lanchonete.commons.type.StatusPedido;
import com.fiap.lanchonete.domain.PedidoDomain;

public interface PedidoGateway {
    PedidoDomain findByIdAndStatusPedido(UUID id, StatusPedido status);

    PedidoDomain findById(UUID id);

    List<PedidoDomain> findAllExcept(List<StatusPedido> listStatusPedido);

    void save(PedidoDomain pedidoDomain);
}
