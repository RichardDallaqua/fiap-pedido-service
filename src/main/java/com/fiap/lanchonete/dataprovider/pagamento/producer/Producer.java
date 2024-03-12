package com.fiap.lanchonete.dataprovider.pagamento.producer;

import com.fiap.lanchonete.dataprovider.pagamento.dto.OrderInfoDTO;
import com.fiap.lanchonete.dataprovider.pagamento.producer.dto.ProducaoDTO;
import com.fiap.lanchonete.dataprovider.pagamento.producer.dto.RealizaPagamentoDTO;

public interface Producer {

    void gerarQrCode(OrderInfoDTO order);

    void realizaPagamento(RealizaPagamentoDTO realizaPagamentoDTO);

    void pagamentoConcluido(ProducaoDTO producaoDTO);
}
