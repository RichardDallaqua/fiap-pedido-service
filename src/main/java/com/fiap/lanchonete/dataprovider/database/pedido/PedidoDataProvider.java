package com.fiap.lanchonete.dataprovider.database.pedido;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.fiap.lanchonete.commons.exception.NotFoundException;
import com.fiap.lanchonete.commons.type.StatusPedido;
import com.fiap.lanchonete.dataprovider.database.pedido.mapper.PedidoDocumentMapper;
import com.fiap.lanchonete.dataprovider.database.pedido.repository.PedidoRepository;
import com.fiap.lanchonete.domain.PedidoDomain;
import com.fiap.lanchonete.services.gateways.PedidoGateway;
import org.springframework.stereotype.Component;

@Component
public class PedidoDataProvider implements PedidoGateway {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoDocumentMapper pedidoDocumentMapper;

    @Override
    public PedidoDomain findByIdAndStatusPedido(UUID id, StatusPedido status) {
        return pedidoDocumentMapper.toDomain(pedidoRepository.findByIdAndStatusPedido(id, status)
                .orElseThrow(() -> new NotFoundException("Pedido não encontrado")));
    }

    @Override
    public PedidoDomain findById(UUID id) {
        return pedidoDocumentMapper.toDomain(
                pedidoRepository.findById(id).orElseThrow(() -> new NotFoundException("Pedido não encontrado")));
    }

    @Override
    public void save(PedidoDomain pedidoDomain) {
        pedidoRepository.save(pedidoDocumentMapper.toDocument(pedidoDomain));
    }

    @Override
    public List<PedidoDomain> findAllExcept(List<StatusPedido> listStatusPedido) {
        return pedidoDocumentMapper.toDomainList(
                pedidoRepository.findAllExcept(Arrays.asList(StatusPedido.PEDIDO_RETIRADO, StatusPedido.CANCELADO)));
    }

}
