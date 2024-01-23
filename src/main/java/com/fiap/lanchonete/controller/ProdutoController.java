package com.fiap.lanchonete.controller;

import com.fiap.lanchonete.controller.dto.ProdutoDTO;
import com.fiap.lanchonete.domain.ProdutoDomain;
import com.fiap.lanchonete.services.ProdutoService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoDomain> cadastrarProduto(@RequestBody @Valid ProdutoDTO produtoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.cadastrarProduto(produtoDTO));
    }

    @GetMapping("/categorias/{categoria}")
    public ResponseEntity<List<ProdutoDomain>> buscarProdutosPorCategoria(@PathVariable("categoria") String categoria) {
        return ResponseEntity.ok(produtoService.buscarProdutosCategoria(categoria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDomain> atualizarProduto(@PathVariable("id") UUID id,
            @RequestBody ProdutoDTO produtoDTO) {
        return ResponseEntity.ok(produtoService.atualizarProduto(id, produtoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable("id") UUID id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
