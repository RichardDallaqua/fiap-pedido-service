package com.fiap.lanchonete.controller;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.lanchonete.controller.dto.ClienteDTO;
import com.fiap.lanchonete.domain.CPFDomain;
import com.fiap.lanchonete.domain.ClienteDomain;
import com.fiap.lanchonete.services.ClienteService;

@RestController
@RequestMapping("api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDomain> obterClientePorId(@PathVariable("id") UUID id) throws Exception {
        ClienteDomain cliente = clienteService.buscarClientePorId(id);

        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ClienteDomain> criarCliente(@RequestBody ClienteDTO clienteDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clienteService.cadastrarCliente(ClienteDomain.builder().id(UUID.randomUUID())
                        .nome(clienteDTO.getNome()).cpf(new CPFDomain(clienteDTO.getCpf()))
                        .telefone(clienteDTO.getTelefone()).dataCadastro(LocalDate.now()).build()));
    }
}
