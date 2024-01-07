package com.fiap.lanchonete.core.applications.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;

import com.fiap.lanchonete.dataprovider.database.cliente.ClienteDataProvider;
import com.fiap.lanchonete.dataprovider.database.pedido.PedidoDataProvider;
import com.fiap.lanchonete.dataprovider.database.produto.ProdutoDataProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;

import com.fiap.lanchonete.commons.type.StatusPagamento;
import com.fiap.lanchonete.commons.type.StatusPedido;
import com.fiap.lanchonete.domain.ClienteDomain;
import com.fiap.lanchonete.domain.PedidoDomain;
import com.fiap.lanchonete.domain.ProdutoDomain;
import com.fiap.lanchonete.fixture.Fixture;
import com.fiap.lanchonete.services.PedidoService;

@MockitoSettings
public class PedidoServiceTest {

    @InjectMocks
    private PedidoService pedidoService;

    @Mock
    private PedidoDataProvider pedidoGateway;

    @Mock
    private ClienteDataProvider clienteGateway;

    @Mock
    private ProdutoDataProvider produtoGateway;

    @Test
    public void testIniciarPedido() {
        // Criando dados de entrada para o teste
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJub21lIjoiUmljaGFyZCBHYWJyaWVsIERhbGxhcXVhIiwiaWQiOiI0MTYzOTc5NTgxMiIsInRlbGVmb25lIjoiNDAwMjg5MjIifQ.iMN8B0cmZWKQQ5IC7_WZsVALBq5qDP8fwv7WPl7u6MI";
        ClienteDomain cliente = Fixture.ClienteFixture.criarClientePadrao();

        // Configurando o comportamento dos mocks
        //Mockito.when(JwtDecode.getCPFFromJWT(Mockito.any())).thenReturn(cpf);
        Mockito.when(clienteGateway.findByCpf(Mockito.any())).thenReturn(cliente);
        Mockito.doNothing().when(pedidoGateway).save(any());
        // Executando o método a ser testado
        PedidoDomain pedido = pedidoService.iniciarPedido(token);

        // Verificando o resultado
        Assertions.assertNotNull(pedido);
        Assertions.assertEquals(StatusPedido.ABERTO, pedido.getStatusPedido());

        Mockito.verify(clienteGateway, Mockito.times(1)).findByCpf(Mockito.any());
        Mockito.verify(pedidoGateway, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void testeIniciarPedidoMasCpfNull() {
        pedidoService.iniciarPedido(null);
        Mockito.verify(clienteGateway, Mockito.times(0)).findByCpf(Mockito.any());
        Mockito.verify(pedidoGateway, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void testAdicionarProdutosPedido() {
        // Criando dados de entrada para o teste
        UUID idPedido = UUID.randomUUID();
        UUID idProduto = UUID.randomUUID();

        PedidoDomain pedido = Fixture.PedidoFixture.criarPedido();
        pedido.setId(idPedido);
        pedido.setStatusPedido(StatusPedido.ABERTO);

        ProdutoDomain produto = new ProdutoDomain();
        produto.setId(idProduto);
        produto.setPreco(BigDecimal.valueOf(10.0));

        PedidoDomain pedidoSalvo = Fixture.PedidoFixture.criarPedido();
        pedidoSalvo.setId(idPedido);
        pedidoSalvo.setStatusPedido(StatusPedido.ABERTO);
        pedidoSalvo.getProdutoList().add(produto);
        pedidoSalvo.setQuantidadeTotalDeItems(1);
        pedidoSalvo.setValorTotalDaCompra(BigDecimal.valueOf(10.0));

        // Configurando o comportamento dos mocks
        Mockito.when(pedidoGateway.findByIdAndStatusPedido(idPedido, StatusPedido.ABERTO))
                .thenReturn(pedido);
        Mockito.when(produtoGateway.findById(idProduto)).thenReturn(produto);
        Mockito.doNothing().when(pedidoGateway).save(any());

        // Executando o método a ser testado
        PedidoDomain pedidoAtualizado = pedidoService.adicionarProdutosPedido(idPedido, idProduto);

        // Verificando o resultado
        Assertions.assertNotNull(pedidoAtualizado);
        Assertions.assertEquals(pedidoSalvo.getId(), pedidoAtualizado.getId());
        Assertions.assertEquals(StatusPedido.ABERTO, pedidoAtualizado.getStatusPedido());
        Assertions.assertEquals(1, pedidoAtualizado.getProdutoList().size());
        Assertions.assertEquals(1, pedidoAtualizado.getQuantidadeTotalDeItems());
        Assertions.assertEquals(BigDecimal.valueOf(10.0), pedidoAtualizado.getValorTotalDaCompra());

        Mockito.verify(pedidoGateway, Mockito.times(1)).findByIdAndStatusPedido(eq(idPedido),
                Mockito.eq(StatusPedido.ABERTO));
        Mockito.verify(produtoGateway, Mockito.times(1)).findById(idProduto);
        Mockito.verify(pedidoGateway, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void testAlterarStatusPedidoSemPagamento() {
        UUID idPedido = UUID.randomUUID();

        PedidoDomain pedido = Fixture.PedidoFixture.criarPedido();
        pedido.setId(idPedido);
        pedido.setStatusPedido(StatusPedido.ABERTO);
        pedido.setStatusPagamento(StatusPagamento.AGUARDANDO_PAGAMENTO);

        Mockito.when(pedidoGateway.findById(any())).thenReturn(pedido);

        assertThrows(PaymentNotApprovedException.class, () -> {
            pedidoService.alterarStatusPedido(idPedido, StatusPedido.PEDIDO_RETIRADO);
        });
    }

    @Test
    public void testRemoverProdutoPedido() {
        UUID idPedido = UUID.randomUUID();
        UUID idProduto = UUID.randomUUID();

        PedidoDomain pedido = Fixture.PedidoFixture.criarPedido();
        pedido.setId(idPedido);
        pedido.setStatusPedido(StatusPedido.ABERTO);

        ProdutoDomain produto = new ProdutoDomain();
        produto.setId(idProduto);
        produto.setPreco(BigDecimal.valueOf(10.0));

        PedidoDomain pedidoSalvo = Fixture.PedidoFixture.criarPedido();
        pedidoSalvo.setId(idPedido);
        pedidoSalvo.setStatusPedido(StatusPedido.ABERTO);
        pedidoSalvo.getProdutoList().add(produto);
        pedidoSalvo.setQuantidadeTotalDeItems(1);
        pedidoSalvo.setValorTotalDaCompra(BigDecimal.valueOf(10.0));

        Mockito.when(pedidoGateway.findByIdAndStatusPedido(idPedido, StatusPedido.ABERTO))
                .thenReturn(pedidoSalvo);

        pedidoService.removerProdutosPedido(idPedido, idProduto);
        Mockito.verify(pedidoGateway, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void testStatusPedidoAlterado() {
        UUID idPedido = UUID.randomUUID();

        PedidoDomain pedido = Fixture.PedidoFixture.criarPedido();
        pedido.setId(idPedido);
        pedido.setStatusPedido(StatusPedido.ABERTO);

        Mockito.when(pedidoGateway.findById(idPedido)).thenReturn(pedido);

        pedidoService.alterarStatusPedido(idPedido, StatusPedido.CANCELADO);

        Mockito.verify(pedidoGateway, Mockito.times(1)).save(Mockito.any());

    }

    @Test
    public void testPedidosNaoFinalizadosListados() {
        pedidoService.listarPedidosNaoFinalizados();
        Mockito.verify(pedidoGateway)
                .findAllExcept(Arrays.asList(StatusPedido.PEDIDO_RETIRADO, StatusPedido.CANCELADO));
    }
}