package com.fiap.lanchonete.services.gateways;

import com.fiap.lanchonete.dataprovider.pagamento.dto.OrderInfoDTO;
import com.fiap.lanchonete.dataprovider.pagamento.producer.dto.ProducaoDTO;
import com.fiap.lanchonete.dataprovider.pagamento.producer.dto.RealizaPagamentoDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public interface PagamentoProducerGateway {

    void gerarQrCode(OrderInfoDTO order);

    void realizaPagamento(RealizaPagamentoDTO realizaPagamentoDTO);

    void pagamentoConcluido(ProducaoDTO producaoDTO);

}
