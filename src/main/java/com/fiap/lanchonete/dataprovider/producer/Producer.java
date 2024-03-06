package com.fiap.lanchonete.dataprovider.producer;

import com.fiap.lanchonete.dataprovider.client.pagamento.dto.OrderInfoDTO;
import com.fiap.lanchonete.dataprovider.producer.dto.ProducaoDTO;
import com.fiap.lanchonete.dataprovider.producer.dto.RealizaPagamentoDTO;

public interface Producer {

    void gerarQrCode(OrderInfoDTO order);

    void realizaPagamento(RealizaPagamentoDTO realizaPagamentoDTO);

    void pagamentoConcluido(ProducaoDTO producaoDTO);
}
