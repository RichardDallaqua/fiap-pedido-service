package com.fiap.lanchonete.dataprovider.database.cliente;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.fiap.lanchonete.commons.exception.NotFoundException;
import com.fiap.lanchonete.dataprovider.database.cliente.documents.ClienteDocument;
import com.fiap.lanchonete.dataprovider.database.cliente.mapper.ClienteDocumentMapper;
import com.fiap.lanchonete.dataprovider.database.cliente.repository.ClienteRepository;
import com.fiap.lanchonete.domain.ClienteDomain;
import com.fiap.lanchonete.services.gateways.ClienteGateway;
import org.springframework.stereotype.Component;

@Component
public class ClienteDataProvider implements ClienteGateway {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public ClienteDomain findById(UUID id) {
        try {
            return ClienteDocumentMapper.toDomain(
                    clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Cliente não encontrado")));
        } catch (NotFoundException e) {
            throw e;
        }
    }

    @Override
    public void save(ClienteDomain clienteDomain) {
        clienteRepository.save(ClienteDocumentMapper.toDocument(clienteDomain));
    }

    @Override
    public ClienteDomain findByCpf(String cpf) {
        return ClienteDocumentMapper.toDomain(
                clienteRepository.findByCpf(cpf).orElseThrow(() -> new NotFoundException("Cliente não encontrado")));
    }

}
