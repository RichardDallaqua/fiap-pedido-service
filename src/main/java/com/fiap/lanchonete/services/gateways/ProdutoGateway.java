package com.fiap.lanchonete.services.gateways;

import java.util.List;
import java.util.UUID;

import com.fiap.lanchonete.domain.ProdutoDomain;

public interface ProdutoGateway {

    List<ProdutoDomain> findAllByCategoria(String categoriaProduto);

    ProdutoDomain findById(UUID id);

    void save(ProdutoDomain produtoDomain);

    void delete(ProdutoDomain produtoDomain);
}
