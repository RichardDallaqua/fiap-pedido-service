package com.fiap.lanchonete.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fiap.lanchonete.commons.type.StatusPagamento;
import com.fiap.lanchonete.commons.type.StatusPedido;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PedidoDomain {
    private UUID id;
    private List<ProdutoDomain> listaProdutos;
    private BigDecimal valorTotalDaCompra;
    private int quantidadeTotalDeItems;
    private ClienteDomain cliente;
    private StatusPedido statusPedido;
    private StatusPagamento statusPagamento;
    private byte[] qrCodePagamento;
}
