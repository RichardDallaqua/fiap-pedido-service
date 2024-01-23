package com.fiap.lanchonete.dataprovider.client.pagamento;

import com.fiap.lanchonete.commons.exception.PaymentNotApprovedException;
import com.fiap.lanchonete.commons.type.StatusPagamento;
import com.fiap.lanchonete.dataprovider.client.pagamento.dto.OrderInfoDTO;
import com.fiap.lanchonete.dataprovider.database.pedido.PedidoDataProvider;
import com.fiap.lanchonete.domain.PedidoDomain;
import com.fiap.lanchonete.services.gateways.PagamentoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class PagamentoDataProvider implements PagamentoGateway {

    private final PagamentoClient client;
    private final PedidoDataProvider pedidoDataProvider;
    @Override
    public byte[] gerarQrCode(final UUID id) {
        var pedido = pedidoDataProvider.findById(id);

        var items = pedido.getListaProdutos().stream().map(item ->
                item.getNome()).collect(Collectors.toList());

        var response = client.gerarQrCode(OrderInfoDTO.builder()
                        .title(pedido.getCliente().getNome())
                        .items(items)
                        .orderIdentifier(id.toString())
                        .totalAmount(pedido.getValorTotalDaCompra())
                .build());

        return validateResponse(response, pedido);
    }

    public String consultarStatusPagamento(final UUID idPedido){
        return client.consultarStatusPagamento(idPedido).getBody().getPaymentStatus();
    }

    private byte[] validateResponse(ResponseEntity<byte[]> response, PedidoDomain pedido) {
        if(response.getStatusCode().is2xxSuccessful()){
            return response.getBody();
        }else{
            pedido.setStatusPagamento(StatusPagamento.PAGAMENTO_NEGADO);
            pedidoDataProvider.save(pedido);
            throw new PaymentNotApprovedException("Não foi possível gerar QR Code para pagamento");
        }
    }
}
