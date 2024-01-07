package com.fiap.lanchonete.dataprovider.database.pedido.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.fiap.lanchonete.commons.type.StatusPedido;
import com.fiap.lanchonete.dataprovider.database.pedido.documents.PedidoDocument;

@Repository
public interface PedidoRepository extends MongoRepository<PedidoDocument, UUID> {

    @Query("{ 'statusPedido' : { $ne: ?0 } }")
    List<PedidoDocument> findAllExcept(List<StatusPedido> statusPedidos);

    Optional<PedidoDocument> findByIdAndStatusPedido(UUID id, StatusPedido statusPedido);
}
