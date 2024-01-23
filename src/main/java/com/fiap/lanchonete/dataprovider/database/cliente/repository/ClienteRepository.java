package com.fiap.lanchonete.dataprovider.database.cliente.repository;

import java.util.Optional;
import java.util.UUID;

import com.fiap.lanchonete.dataprovider.database.cliente.documents.ClienteDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends MongoRepository<ClienteDocument, UUID> {

    Optional<ClienteDocument> findByCpf(String cpf);
}
