package com.fiap.lanchonete.dataprovider.client.pagamento;

import com.fiap.lanchonete.commons.exception.PaymentNotApprovedException;
import com.fiap.lanchonete.commons.type.StatusPagamento;
import com.fiap.lanchonete.dataprovider.client.pagamento.dto.OrderInfoDTO;
import com.fiap.lanchonete.dataprovider.database.pedido.PedidoDataProvider;
import com.fiap.lanchonete.dataprovider.producer.PagamentoProducer;
import com.fiap.lanchonete.dataprovider.producer.Producer;
import com.fiap.lanchonete.dataprovider.producer.dto.RealizaPagamentoDTO;
import com.fiap.lanchonete.domain.ClienteDomain;
import com.fiap.lanchonete.domain.PedidoDomain;
import com.fiap.lanchonete.domain.ProdutoDomain;
import com.fiap.lanchonete.services.gateways.PagamentoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class PagamentoDataProvider implements PagamentoGateway {

    private final PagamentoClient client;
    private final Producer producer;
    private final PedidoDataProvider pedidoDataProvider;

    public void gerarQrCode(final UUID id) {
        //var pedido = pedidoDataProvider.findById(id);

        var pedido = PedidoDomain.builder()
                .id(id)
                .cliente(ClienteDomain.builder().nome("Fulano").build())
                .listaProdutos(List.of(
                        ProdutoDomain.builder().nome("xbacon").build(),
                        ProdutoDomain.builder().nome("Coca-Cola").build()
                ))
                .valorTotalDaCompra(BigDecimal.valueOf(120))
                .build();

        var items = pedido.getListaProdutos().stream().map(item ->
                item.getNome()).collect(Collectors.toList());

        if(!items.isEmpty()){
            producer.gerarQrCode(OrderInfoDTO.builder()
                        .title(pedido.getCliente().getNome())
                        .items(items)
                        .orderIdentifier(id.toString())
                        .totalAmount(pedido.getValorTotalDaCompra())
                .build());
        }
    }

    public void realizarPagamento(final UUID id) {
        producer.realizaPagamento(RealizaPagamentoDTO.builder()
                        .orderIdentifier(id.toString())
                        .status("APROVADO")
                .build());
    }

}
