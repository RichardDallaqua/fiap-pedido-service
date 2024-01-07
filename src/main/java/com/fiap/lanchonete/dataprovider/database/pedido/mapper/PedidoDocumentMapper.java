package com.fiap.lanchonete.dataprovider.database.pedido.mapper;

import java.util.List;

import com.fiap.lanchonete.domain.CPFDomain;
import org.mapstruct.Mapper;

import com.fiap.lanchonete.dataprovider.database.pedido.documents.PedidoDocument;
import com.fiap.lanchonete.domain.PedidoDomain;

@Mapper(componentModel = "spring")
public interface PedidoDocumentMapper {

    PedidoDomain toDomain(PedidoDocument document);

    List<PedidoDomain> toDomainList(List<PedidoDocument> document);

    PedidoDocument toDocument(PedidoDomain domain);

    String toCPFString(CPFDomain domain);

    CPFDomain toCPFDomain(String CPF);
}
