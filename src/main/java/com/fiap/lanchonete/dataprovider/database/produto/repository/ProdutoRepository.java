package com.fiap.lanchonete.dataprovider.database.produto.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fiap.lanchonete.commons.type.CategoriaProduto;
import com.fiap.lanchonete.dataprovider.database.produto.document.ProdutoDocument;
import com.fiap.lanchonete.domain.ProdutoDomain;

@Repository
public interface ProdutoRepository extends MongoRepository<ProdutoDocument, UUID> {

    List<ProdutoDocument> findAllByCategoria(CategoriaProduto categoriaProduto);
}
