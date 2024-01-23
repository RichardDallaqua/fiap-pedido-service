package com.fiap.lanchonete.services.gateways;

import java.util.UUID;

import com.fiap.lanchonete.domain.ClienteDomain;

public interface ClienteGateway {

    ClienteDomain findById(UUID id);

    ClienteDomain findByCpf(String cpf);

    void save(ClienteDomain clienteDomain);
}
