package com.fiap.lanchonete.dataprovider.database.pedido.documents;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fiap.lanchonete.commons.type.StatusPagamento;
import com.fiap.lanchonete.commons.type.StatusPedido;
import com.fiap.lanchonete.dataprovider.database.cliente.documents.ClienteDocument;
import com.fiap.lanchonete.dataprovider.database.produto.document.ProdutoDocument;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "pedidos")
public class PedidoDocument {

    @Id
    private UUID id;
    private List<ProdutoDocument> produtoList;
    private BigDecimal valorTotalDaCompra;
    private int quantidadeTotalDeItems;
    private ClienteDocument cliente;
    private StatusPedido statusPedido;
    private StatusPagamento statusPagamento;
}
