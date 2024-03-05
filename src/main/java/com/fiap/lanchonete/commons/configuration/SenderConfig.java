package com.fiap.lanchonete.commons.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SenderConfig {

    @Value("${queue01.gerar_qr_code}")
    private String geraQrCode;

    @Bean
    public Queue geraQrCode() {
        return new Queue(geraQrCode, true);
    }
}
