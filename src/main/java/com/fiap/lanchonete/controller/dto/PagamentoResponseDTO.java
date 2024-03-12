package com.fiap.lanchonete.controller.dto;

import com.fiap.lanchonete.commons.type.StatusPagamento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagamentoResponseDTO implements Serializable {
    private UUID idPedido;
    private StatusPagamento status;
}
