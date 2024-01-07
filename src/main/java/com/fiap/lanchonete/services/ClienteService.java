package com.fiap.lanchonete.services;

import com.fiap.lanchonete.dataprovider.database.cliente.ClienteDataProvider;
import com.fiap.lanchonete.domain.ClienteDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClienteService {

    @Autowired
    private ClienteDataProvider clienteDataProvider;

    public ClienteDomain cadastrarCliente(ClienteDomain cliente) {
        clienteDataProvider.save(cliente);
        return cliente;
    }

    public ClienteDomain buscarClientePorId(UUID id) throws Exception {
        return clienteDataProvider.findById(id);
    }
}
