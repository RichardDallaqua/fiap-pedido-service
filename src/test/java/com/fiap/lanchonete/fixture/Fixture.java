package com.fiap.lanchonete.fixture;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import com.fiap.lanchonete.commons.type.CategoriaProduto;
import com.fiap.lanchonete.commons.type.StatusPedido;
import com.fiap.lanchonete.controller.dto.ClienteDTO;
import com.fiap.lanchonete.controller.dto.ProdutoDTO;
import com.fiap.lanchonete.dataprovider.database.cliente.documents.ClienteDocument;
import com.fiap.lanchonete.dataprovider.database.pedido.documents.PedidoDocument;
import com.fiap.lanchonete.domain.CPFDomain;
import com.fiap.lanchonete.domain.ClienteDomain;
import com.fiap.lanchonete.domain.PedidoDomain;
import com.fiap.lanchonete.domain.ProdutoDomain;

public class Fixture {
    public class ProdutoFixture {

        public static ProdutoDomain createProduto() {
            return ProdutoDomain.builder().id(UUID.randomUUID()).nome("Produto Teste")
                    .descricao("Descrição do Produto Teste").preco(new BigDecimal("9.99"))
                    .categoria(CategoriaProduto.BEBIDA).build();
        }
    }

    public class ProdutoDTOFixture {

        public static ProdutoDTO createProdutoDTO() {
            return ProdutoDTO.builder().nome("Produto Teste").descricao("Descrição do Produto Teste")
                    .preco(new BigDecimal(9.99)).categoria(CategoriaProduto.BEBIDA).build();
        }
    }

    public class ClienteFixture {
        public static ClienteDomain criarClientePadrao() {
            return ClienteDomain.builder().id(UUID.randomUUID()).nome("Richard Dallaqua")
                    .cpf(new CPFDomain("02366792018")).telefone("1234567890").dataCadastro(LocalDate.of(1990, 1, 1))
                    .build();
        }

    }


    public class ClienteDTOFixture {
        public static ClienteDTO criarClienteDTOPadrao() {
            return ClienteDTO.builder().nome("Richard Dallaqua").cpf("02366792018").telefone("1234567890").build();
        }
    }

    public class PedidoFixture {

        public static PedidoDomain criarPedido() {
            return PedidoDomain.builder().id(UUID.randomUUID()).produtoList(new ArrayList<>())
                    .valorTotalDaCompra(BigDecimal.ZERO).quantidadeTotalDeItems(0)
                    .cliente(ClienteFixture.criarClientePadrao()).statusPedido(StatusPedido.ABERTO).build();
        }
    }
}
