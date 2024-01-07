package com.fiap.lanchonete.core.applications.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.fiap.lanchonete.dataprovider.database.produto.ProdutoDataProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoSettings;

import com.fiap.lanchonete.commons.exception.NotFoundException;
import com.fiap.lanchonete.commons.type.CategoriaProduto;
import com.fiap.lanchonete.controller.dto.ProdutoDTO;
import com.fiap.lanchonete.dataprovider.database.produto.repository.ProdutoRepository;
import com.fiap.lanchonete.domain.ProdutoDomain;
import com.fiap.lanchonete.fixture.Fixture;
import com.fiap.lanchonete.services.ProdutoService;

@MockitoSettings
class ProdutoServiceTest {

    @Mock
    private ProdutoDataProvider produtoGateway;;

    @InjectMocks
    private ProdutoService produtoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCadastrarProduto() {
        // Dados de entrada para o teste
        ProdutoDTO produtoDTO = Fixture.ProdutoDTOFixture.createProdutoDTO();

        // Comportamento esperado do mock do repositório
        doNothing().when(produtoGateway).save(any(ProdutoDomain.class));

        // Chamada do método a ser testado
        ProdutoDomain result = produtoService.cadastrarProduto(produtoDTO);

        // Verificações
        verify(produtoGateway, times(1)).save(any(ProdutoDomain.class));
        Assertions.assertNotNull(result);
    }

    @Test
    void testBuscarProdutosCategoria() {
        // Dados de entrada para o teste
        String categoria = CategoriaProduto.BEBIDA.toString();

        // Comportamento esperado do mock do repositório
        List<ProdutoDomain> produtos = new ArrayList<>();
        when(produtoGateway.findAllByCategoria(any())).thenReturn(produtos);

        // Chamada do método a ser testado
        List<ProdutoDomain> result = produtoService.buscarProdutosCategoria(categoria);

        // Verificações
        verify(produtoGateway, times(1)).findAllByCategoria(any());
        Assertions.assertNotNull(result);
    }

    @Test
    void testAtualizarProduto() {
        // Dados de entrada para o teste
        UUID id = UUID.randomUUID();
        ProdutoDTO produtoDTO = Fixture.ProdutoDTOFixture.createProdutoDTO();

        // Comportamento esperado do mock do repositório
        ProdutoDomain produtoMock = Fixture.ProdutoFixture.createProduto();
        when(produtoGateway.findById(any())).thenReturn(produtoMock);
        doNothing().when(produtoGateway).save(any(ProdutoDomain.class));

        // Chamada do método a ser testado
        ProdutoDomain result = produtoService.atualizarProduto(id, produtoDTO);

        // Verificações
        verify(produtoGateway, times(1)).findById(any());
        verify(produtoGateway, times(1)).save(any(ProdutoDomain.class));
        Assertions.assertNotNull(result);
    }

    @Test
    void testDeletarProduto() {
        // Dados de entrada para o teste
        UUID id = UUID.randomUUID();

        // Comportamento esperado do mock do repositório
        ProdutoDomain produtoMock = Fixture.ProdutoFixture.createProduto();
        when(produtoGateway.findById(any())).thenReturn(produtoMock);

        // Chamada do método a ser testado
        produtoService.deletarProduto(id);

        // Verificações
        verify(produtoGateway, times(1)).findById(any());
        verify(produtoGateway, times(1)).delete(any(ProdutoDomain.class));
    }

    @Test
    void testDeveRetornarErroSeNaoEncontrarUmProdutoParaDeletar() {
        // Dados de entrada para o teste
        UUID id = UUID.randomUUID();

        // Comportamento esperado do mock do repositório
        when(produtoGateway.findById(any())).thenThrow(NotFoundException.class);

        // Chamada do método a ser testado e validação
        Assertions.assertThrows(NotFoundException.class, () -> produtoService.deletarProduto(id));
    }
}
