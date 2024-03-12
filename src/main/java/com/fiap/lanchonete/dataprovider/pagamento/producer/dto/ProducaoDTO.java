package com.fiap.lanchonete.dataprovider.pagamento.producer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProducaoDTO {

    private UUID idPedido;
    private String status;
}
