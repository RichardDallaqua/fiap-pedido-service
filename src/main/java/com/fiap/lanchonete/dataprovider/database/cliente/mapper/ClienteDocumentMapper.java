package com.fiap.lanchonete.dataprovider.database.cliente.mapper;

import com.fiap.lanchonete.dataprovider.database.cliente.documents.CPFDocument;
import com.fiap.lanchonete.dataprovider.database.cliente.documents.ClienteDocument;
import com.fiap.lanchonete.domain.CPFDomain;
import com.fiap.lanchonete.domain.ClienteDomain;

public class ClienteDocumentMapper {

    public static ClienteDomain toDomain(ClienteDocument document) {
        return ClienteDomain.builder().id(document.getId()).nome(document.getNome())
                .cpf(new CPFDomain(document.getCpf().getNumero())).telefone(document.getTelefone())
                .dataCadastro(document.getDataCadastro()).build();
    }

    public static ClienteDocument toDocument(ClienteDomain domain) {
        return ClienteDocument.builder()
                .id(domain.getId())
                .nome(domain.getNome())
                .cpf(new CPFDocument(domain.getCpf().getNumero()))
                .telefone(domain.getTelefone())
                .dataCadastro(domain.getDataCadastro()).build();
    }

}
