package com.fiap.lanchonete.dataprovider.client.pagamento;

import com.fiap.lanchonete.dataprovider.client.pagamento.dto.OrderInfoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class PagamentoClient {

    @Value("${host.payment}")
    private String url;
    RestTemplate restTemplate;

    public ResponseEntity<byte[]> gerarQrCode(final OrderInfoDTO orderInfoDTO){
        HttpEntity<OrderInfoDTO> request = new HttpEntity<>(orderInfoDTO);
        return restTemplate.exchange(url + "/generate/qr-code", HttpMethod.POST, request, byte[].class);
    }

    public ResponseEntity<OrderInfoDTO> consultarStatusPagamento(final UUID idPedido){
        return restTemplate.exchange(url + idPedido.toString(), HttpMethod.GET, null, OrderInfoDTO.class);
    }

}
