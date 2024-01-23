package com.fiap.lanchonete.commons.configuration;

import org.bson.UuidRepresentation;
import org.springframework.boot.autoconfigure.mongo.MongoClientSettingsBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoDBConfig {

    @Bean
    public MongoClientSettingsBuilderCustomizer mongoDBDefaultSettings() {
        return builder -> builder.uuidRepresentation(UuidRepresentation.STANDARD);
    }
}
