package com.fiap.lanchonete.services;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.fiap.lanchonete.dataprovider.database.produto.ProdutoDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.lanchonete.commons.type.CategoriaProduto;
import com.fiap.lanchonete.controller.dto.ProdutoDTO;
import com.fiap.lanchonete.domain.ProdutoDomain;
import com.fiap.lanchonete.services.gateways.ProdutoGateway;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoDataProvider produtoGateway;

    public ProdutoDomain cadastrarProduto(ProdutoDTO produtoDTO) {
        ProdutoDomain produto = ProdutoDomain.builder().id(UUID.randomUUID()).nome(produtoDTO.getNome())
                .descricao(produtoDTO.getDescricao()).preco(produtoDTO.getPreco()).categoria(produtoDTO.getCategoria())
                .build();
        produtoGateway.save(produto);
        return produto;
    }

    public List<ProdutoDomain> buscarProdutosCategoria(String categoria) {
        CategoriaProduto.existsInValues(categoria);
        return produtoGateway.findAllByCategoria(categoria.toUpperCase());
    }

    public ProdutoDomain atualizarProduto(UUID id, ProdutoDTO produtoDTO) {
        ProdutoDomain produtoToUpdate = produtoGateway.findById(id);
        ProdutoDomain produtoAtualizado = new ProdutoDomain();
        produtoAtualizado.setId(produtoToUpdate.getId());
        produtoAtualizado.setNome(Boolean.TRUE.equals(Objects.nonNull(produtoDTO.getNome())) ? produtoDTO.getNome()
                : produtoToUpdate.getNome());
        produtoAtualizado.setDescricao(Boolean.TRUE.equals(Objects.nonNull(produtoDTO.getDescricao()))
                ? produtoDTO.getDescricao() : produtoToUpdate.getDescricao());
        produtoAtualizado.setPreco(Boolean.TRUE.equals(Objects.nonNull(produtoDTO.getPreco())) ? produtoDTO.getPreco()
                : produtoToUpdate.getPreco());
        produtoAtualizado.setCategoria(Boolean.TRUE.equals(Objects.nonNull(produtoDTO.getCategoria()))
                ? produtoDTO.getCategoria() : produtoToUpdate.getCategoria());
        if (Objects.equals(produtoToUpdate, produtoAtualizado)) {
            return produtoToUpdate;
        } else {
            produtoGateway.save(produtoAtualizado);
            return produtoAtualizado;
        }
    }

    public void deletarProduto(UUID id) {
        ProdutoDomain produtoToDelete = produtoGateway.findById(id);
        produtoGateway.delete(produtoToDelete);
    }
}
