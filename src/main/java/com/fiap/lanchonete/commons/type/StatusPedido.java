package com.fiap.lanchonete.commons.type;

import java.util.Arrays;

import com.fiap.lanchonete.commons.exception.StatusNotAllowedException;

import lombok.Getter;

@Getter
public enum StatusPedido {
    ABERTO(1, new Integer[] { 2, 3, 4, 5, 6 }), CANCELADO(2, new Integer[] {}),
    RECEBIDO(3, new Integer[] { 2, 4, 5, 6 }), PREPARANDO_PEDIDO(4, new Integer[] { 5, 6 }),
    RETIRAR_PEDIDO(5, new Integer[] { 6 }), PEDIDO_RETIRADO(6, new Integer[] {});

    private Integer order;
    private Integer[] allowedUpdate;

    StatusPedido(Integer order, Integer[] allowedUpdate) {
        this.order = order;
        this.allowedUpdate = allowedUpdate;
    }

    public static void verifyOrderOnUpdate(StatusPedido oldStatus, StatusPedido nextStatus) {
        if (!Arrays.asList(oldStatus.getAllowedUpdate()).contains(nextStatus.getOrder())) {
            throw new StatusNotAllowedException(
                    String.format("Status %s não pode ser atribuido a esse pedido", nextStatus.name()));
        }
    }

}
