package com.fiap.lanchonete.dataprovider.database.produto;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.fiap.lanchonete.commons.exception.NotFoundException;
import com.fiap.lanchonete.commons.type.CategoriaProduto;
import com.fiap.lanchonete.dataprovider.database.produto.mapper.ProdutoDocumentMapper;
import com.fiap.lanchonete.dataprovider.database.produto.repository.ProdutoRepository;
import com.fiap.lanchonete.domain.ProdutoDomain;
import com.fiap.lanchonete.services.gateways.ProdutoGateway;
import org.springframework.stereotype.Component;

@Component
public class ProdutoDataProvider implements ProdutoGateway {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoDocumentMapper produtoDocumentMapper;

    @Override
    public ProdutoDomain findById(UUID id) {
        return produtoDocumentMapper.toDomain(
                produtoRepository.findById(id).orElseThrow(() -> new NotFoundException("Produto não encontrado")));
    }

    @Override
    public List<ProdutoDomain> findAllByCategoria(String categoriaProduto) {
        return produtoDocumentMapper.toDomainList(
                produtoRepository.findAllByCategoria(CategoriaProduto.valueOf(categoriaProduto.toUpperCase())));
    }

    @Override
    public void save(ProdutoDomain produtoDomain) {
        produtoRepository.save(produtoDocumentMapper.toDocument(produtoDomain));
    }

    @Override
    public void delete(ProdutoDomain produtoDomain) {
        produtoRepository.delete(produtoDocumentMapper.toDocument(produtoDomain));
    }

}
