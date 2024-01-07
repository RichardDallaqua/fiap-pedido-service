package com.fiap.lanchonete.dataprovider.database.cliente.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fiap.lanchonete.dataprovider.database.cliente.documents.ClienteDocument;
import com.fiap.lanchonete.domain.CPFDomain;
import com.fiap.lanchonete.domain.ClienteDomain;

@Repository
public interface ClienteRepository extends MongoRepository<ClienteDocument, UUID> {

    Optional<ClienteDocument> findByCpf(String cpf);
}
