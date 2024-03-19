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
        connectionFactory.setUri("amqps://b-4885be68-aa8d-41e7-82e6-71dc375cb847.mq.us-east-1.amazonaws.com:5671");
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
