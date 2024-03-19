package com.fiap.lanchonete.commons.configuration;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setUri("amqps://b-d53c4a24-dce4-4c3d-b07e-ebb6fe7981a3.mq.us-east-2.amazonaws.com:5671");
        connectionFactory.setPort(5671);
        connectionFactory.setVirtualHost("rabbit");
        connectionFactory.setUsername("teste");
        connectionFactory.setPassword("teste123123123");
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(){
        return new RabbitTemplate(connectionFactory());
    }
}
