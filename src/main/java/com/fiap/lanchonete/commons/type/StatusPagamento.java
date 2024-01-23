package com.fiap.lanchonete.commons.type;

import java.util.Arrays;

import com.fiap.lanchonete.commons.exception.InvalidTypeException;

public enum StatusPagamento {
    AGUARDANDO_PAGAMENTO, PAGAMENTO_APROVADO, PAGAMENTO_NEGADO;

    public static void existsInValues(String statusPagamento) {
        if (Arrays.stream(values()).noneMatch(x -> x.name().equalsIgnoreCase(statusPagamento))) {
            throw new InvalidTypeException(String.format("O status do pedido %s é inválido", statusPagamento));
        }
    }
}
