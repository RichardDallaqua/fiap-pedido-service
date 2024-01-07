package com.fiap.lanchonete.dataprovider.pagamento;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
@RequiredArgsConstructor
public class PagamentoClient {

    @Value("${host.payment}")
    private String url;
    RestTemplate restTemplate;

    public void realizarPagamento(){
        restTemplate.exchange(url, HttpMethod.PUT, );

    }

}
