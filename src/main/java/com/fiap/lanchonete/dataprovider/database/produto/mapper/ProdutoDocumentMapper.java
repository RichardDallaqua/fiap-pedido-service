package com.fiap.lanchonete.dataprovider.database.produto.mapper;

import java.util.List;

import com.fiap.lanchonete.dataprovider.database.produto.document.ProdutoDocument;
import com.fiap.lanchonete.domain.ProdutoDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoDocumentMapper {

    ProdutoDomain toDomain(ProdutoDocument document);

    List<ProdutoDomain> toDomainList(List<ProdutoDocument> document);

    ProdutoDocument toDocument(ProdutoDomain domain);

}
