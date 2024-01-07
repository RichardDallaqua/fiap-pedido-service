package com.fiap.lanchonete;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.fiap.lanchonete")
public class LanchoneteApplication {

    public static void main(String[] args) {
        SpringApplication.run(LanchoneteApplication.class, args);
    }

}
