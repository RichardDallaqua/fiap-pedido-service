package com.fiap.lanchonete.controller;

import com.fiap.lanchonete.domain.PedidoDomain;
import com.fiap.lanchonete.services.PedidoService;
import io.cucumber.java.it.Quando;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Então;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static com.fiap.lanchonete.fixture.Fixture.PedidoFixture.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@MockitoSettings(strictness = Strictness.LENIENT)
class PedidoControllerStepDefinition {

    @Mock
    private PedidoService pedidoService;
    @InjectMocks
    private PedidoController controller;
    private List<PedidoDomain> pedidos;
    private ResponseEntity<PedidoDomain> responseEntity;
    private ResponseEntity<List<PedidoDomain>> responseEntityPedidos;
    private UUID idPedido;
    private UUID idProduto;
    private String auth;

    @Dado("um usuário autorizado")
    public void um_usuário_autorizado() {
        auth = "mockedAuth";
    }
    @Quando("o usuário inicia um novo pedido")
    public void o_usuário_inicia_um_novo_pedido() {
        when(pedidoService.iniciarPedido(auth)).thenReturn(criarPedido());
        responseEntity = controller.iniciarPedido(auth);
    }
    @Então("o pedido é criado com sucesso")
    public void o_pedido_é_criado_com_sucesso() {
        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }
    @Test
    public void test(){
        um_usuário_autorizado();
        o_usuário_inicia_um_novo_pedido();
        o_pedido_é_criado_com_sucesso();
    }

    @Dado("um pedido existente")
    public void um_pedido_existente() {
        when(pedidoService.iniciarPedido(auth)).thenReturn(criarPedido());
        responseEntity = controller.iniciarPedido(auth);
        idPedido = responseEntity.getBody().getId();
    }
    @Quando("o usuário adiciona um produto ao pedido")
    public void o_usuário_adiciona_um_produto_ao_pedido() {
        idProduto = UUID.randomUUID();
        when(pedidoService.adicionarProdutosPedido(idPedido, idProduto))
                .thenReturn(criarPedido());
        responseEntity = controller.adicionarProdutos(idPedido, idProduto);
    }
    @Então("o produto é adicionado com sucesso")
    public void o_produto_é_adicionado_com_sucesso() {
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void test02(){
        um_usuário_autorizado();
        um_pedido_existente();
        o_usuário_adiciona_um_produto_ao_pedido();
        o_produto_é_adicionado_com_sucesso();
    }

    @Dado("um pedido existente com produtos")
    public void um_pedido_existente_com_produtos() {
        idProduto = UUID.randomUUID();
        when(pedidoService.adicionarProdutosPedido(idPedido, idProduto))
                .thenReturn(criarPedido());
        responseEntity = controller.adicionarProdutos(idPedido, idProduto);
    }
    @Quando("o usuário remove um produto do pedido")
    public void o_usuário_remove_um_produto_do_pedido() {
        when(pedidoService.removerProdutosPedido(idPedido, idProduto)).thenReturn(criarPedido());
        responseEntity = controller.removerProdutos(idPedido, idProduto);
    }
    @Então("o produto é removido com sucesso")
    public void o_produto_é_removido_com_sucesso() {
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void test03(){
        um_usuário_autorizado();
        um_pedido_existente_com_produtos();
        o_usuário_remove_um_produto_do_pedido();
        o_produto_é_removido_com_sucesso();
    }

    @Dado("pedidos existentes")
    public void pedidos_existentes() {
        when(pedidoService.iniciarPedido("auth01")).thenReturn(criarPedido());
        when(pedidoService.iniciarPedido("auth02")).thenReturn(criarPedido());
        pedidos = List.of(criarPedido(), criarPedido());
    }
    @Quando("o usuário solicita a listagem de todos os pedidos")
    public void o_usuário_solicita_a_listagem_de_todos_os_pedidos() {
        when(pedidoService.listarPedidosNaoFinalizados()).thenReturn(pedidos);
        responseEntityPedidos = controller.listarPedidos();
    }
    @Então("a lista de pedidos é retornada com sucesso")
    public void a_lista_de_pedidos_é_retornada_com_sucesso() {
        Assert.assertEquals(HttpStatus.OK, responseEntityPedidos.getStatusCode());
    }

    @Test
    public void test04(){
        um_usuário_autorizado();
        pedidos_existentes();
        o_usuário_solicita_a_listagem_de_todos_os_pedidos();
        a_lista_de_pedidos_é_retornada_com_sucesso();
    }

}