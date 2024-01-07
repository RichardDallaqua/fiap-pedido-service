package com.fiap.lanchonete.domain;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteDomain {
    private UUID id;
    private String nome;
    private CPFDomain cpf;
    private String telefone;
    private LocalDate dataCadastro;
}
