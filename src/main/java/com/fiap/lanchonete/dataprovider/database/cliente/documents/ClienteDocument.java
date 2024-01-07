package com.fiap.lanchonete.dataprovider.database.cliente.documents;

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
@Document(collection = "clientes")
public class ClienteDocument {

    @Id
    private UUID id;
    private String nome;
    private CPFDocument cpf;
    private String telefone;
    private LocalDate dataCadastro;
}
