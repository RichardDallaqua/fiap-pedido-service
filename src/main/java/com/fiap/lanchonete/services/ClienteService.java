package com.fiap.lanchonete.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.lanchonete.dataprovider.database.ClienteDataProvider;
import com.fiap.lanchonete.domain.ClienteDomain;

@Service
public class ClienteService {

    @Autowired
    private ClienteDataProvider clienteDataProvider;

    public ClienteDomain cadastrarCliente(ClienteDomain cliente) {
        clienteDataProvider.save(cliente);
        return cliente;
    }

    public ClienteDomain buscarClientePorId(UUID id) {
        return clienteDataProvider.findById(id);
    }

    public void apagarDadosCliente(String cpf) {
        clienteDataProvider.apagarDadosCliente(cpf);
    }
}
