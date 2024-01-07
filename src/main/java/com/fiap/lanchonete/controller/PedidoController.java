package com.fiap.lanchonete.controller;

import com.fiap.lanchonete.commons.type.StatusPagamento;
import com.fiap.lanchonete.commons.type.StatusPedido;
import com.fiap.lanchonete.domain.PedidoDomain;
import com.fiap.lanchonete.services.PedidoService;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoDomain> iniciarPedido(@RequestHeader String authorization) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.iniciarPedido(authorization));
    }

    @PutMapping("/{idPedido}/adicionar/{idProduto}")
    public ResponseEntity<PedidoDomain> adicionarProdutos(@PathVariable("idPedido") UUID idPedido,
            @PathVariable("idProduto") UUID idProduto) {
        return ResponseEntity.ok(pedidoService.adicionarProdutosPedido(idPedido, idProduto));
    }

    @PutMapping("/{idPedido}/remover/{idProduto}")
    public ResponseEntity<PedidoDomain> removerProdutos(@PathVariable("idPedido") UUID idPedido,
            @PathVariable("idProduto") UUID idProduto) {
        return ResponseEntity.ok(pedidoService.removerProdutosPedido(idPedido, idProduto));
    }

    @GetMapping()
    public ResponseEntity<List<PedidoDomain>> listarPedidos() {
        return ResponseEntity.ok(pedidoService.listarPedidosNaoFinalizados());
    }

    @GetMapping("/{idPedido}/statusPagamento")
    public ResponseEntity<StatusPagamento> alterarStatus(@PathVariable("idPedido") UUID idPedido) {
        return ResponseEntity.ok(pedidoService.buscarStatusPagamento(idPedido));
    }

    @GetMapping("/{idPedido}/buscar")
    public ResponseEntity<PedidoDomain> listarDadosPedido(@PathVariable("idPedido") UUID idPedido) {
        return ResponseEntity.ok(pedidoService.listarDadosDoPedido(idPedido));
    }

    @PutMapping("/{idPedido}/status/{status}")
    public ResponseEntity<Void> alterarStatus(@PathVariable("idPedido") UUID idPedido,
            @PathVariable("status") StatusPedido statusPedido) {
        pedidoService.alterarStatusPedido(idPedido, statusPedido);
        return ResponseEntity.noContent().build();
    }
}
