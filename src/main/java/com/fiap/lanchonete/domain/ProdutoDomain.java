package com.fiap.lanchonete.domain;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fiap.lanchonete.commons.type.CategoriaProduto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDomain {
    private UUID id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private CategoriaProduto categoria;
}
