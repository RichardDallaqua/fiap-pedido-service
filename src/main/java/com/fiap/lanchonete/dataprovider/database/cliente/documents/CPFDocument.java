package com.fiap.lanchonete.dataprovider.database.cliente.documents;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
public class CPFDocument {
    private String numero;
}
