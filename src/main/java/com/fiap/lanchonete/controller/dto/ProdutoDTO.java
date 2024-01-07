package com.fiap.lanchonete.controller.dto;

import java.math.BigDecimal;

import com.fiap.lanchonete.commons.type.CategoriaProduto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProdutoDTO {
    @NotBlank(message = "Nome não pode ser vazio ou nulo")
    private String nome;
    @NotBlank(message = "Descrição não pode ser vazio ou nulo")
    private String descricao;
    @DecimalMin(value = "0", inclusive = false, message = "Preço não pode ser menor ou igual a zero")
    private BigDecimal preco;
    @NotNull(message = "Categoria não pode ser nulo")
    private CategoriaProduto categoria;
}
