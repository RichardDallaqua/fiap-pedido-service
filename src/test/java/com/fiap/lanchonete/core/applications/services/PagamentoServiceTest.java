package com.fiap.lanchonete.core.applications.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.UUID;

import com.fiap.lanchonete.dataprovider.database.pedido.PedidoDataProvider;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;

import com.fiap.lanchonete.commons.exception.EmptyOrderException;
import com.fiap.lanchonete.commons.exception.NotFoundException;
import com.fiap.lanchonete.commons.type.StatusPagamento;
import com.fiap.lanchonete.commons.type.StatusPedido;
import com.fiap.lanchonete.controller.dto.PagamentoResponseDTO;
import com.fiap.lanchonete.dataprovider.pagamento.PagamentoClient;
import com.fiap.lanchonete.domain.PedidoDomain;
import com.fiap.lanchonete.domain.ProdutoDomain;
import com.fiap.lanchonete.fixture.Fixture;

@MockitoSettings
public class PagamentoServiceTest {

    @Mock
    private PagamentoClient pagamentoClient;

    @Mock
    private PedidoDataProvider pedidoGateway;

    @InjectMocks
    private PagamentoService pagamentoService;

    @Test
    void testPedidoNaoEncontrado() {
        Mockito.when(pedidoGateway.findByIdAndStatusPedido(any(), any())).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> pagamentoService.realizarPagamento(UUID.randomUUID()));
    }

    @Test
    void testPedidoVazio() {
        PedidoDomain pedidoSalvo = Fixture.PedidoFixture.criarPedido();
        pedidoSalvo.setId(UUID.randomUUID());
        pedidoSalvo.setStatusPedido(StatusPedido.ABERTO);

        Mockito.when(pedidoGateway.findByIdAndStatusPedido(any(), any())).thenReturn(pedidoSalvo);

        assertThrows(EmptyOrderException.class, () -> {
            pagamentoService.realizarPagamento(UUID.randomUUID());
        });
    }

    @Test
    void testPagamentoRealizado() {
        PedidoDomain pedidoSalvo = Fixture.PedidoFixture.criarPedido();
        pedidoSalvo.setId(UUID.randomUUID());
        pedidoSalvo.setStatusPedido(StatusPedido.ABERTO);
        pedidoSalvo.setProdutoList(Collections.singletonList(ProdutoDomain.builder().build()));

        Mockito.when(pedidoGateway.findByIdAndStatusPedido(any(), any())).thenReturn(pedidoSalvo);
        PagamentoResponseDTO clientResponse = new PagamentoResponseDTO();
        clientResponse.setStatus(StatusPagamento.PAGAMENTO_APROVADO);
        Mockito.doNothing().when(pagamentoClient).realizarPagamento(any());

        pagamentoService.realizarPagamento(pedidoSalvo.getId());
    }
}
