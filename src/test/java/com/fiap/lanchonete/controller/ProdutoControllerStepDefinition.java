package com.fiap.lanchonete.controller;

import com.fiap.lanchonete.controller.dto.ProdutoDTO;
import com.fiap.lanchonete.domain.ProdutoDomain;
import com.fiap.lanchonete.fixture.Fixture;
import com.fiap.lanchonete.services.ProdutoService;
import io.cucumber.java.it.Quando;
import io.cucumber.java.pt.Dado;
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

import java.util.UUID;

@MockitoSettings(strictness = Strictness.LENIENT)
class ProdutoControllerStepDefinition {

    @Mock
    private ProdutoService produtoService;
    @InjectMocks
    private ProdutoController produtoController;
    private ProdutoDTO produtoDTO;
    private ProdutoDomain produtoDomain;
    private ResponseEntity<ProdutoDomain> produtoDomainResponseEntity;

    @Dado("que eu tenho um produto com os seguintes detalhes")
    public void que_eu_tenho_um_produto_com_os_seguintes_detalhes() {
        produtoDTO = Fixture.ProdutoDTOFixture.createProdutoDTO();
    }

    @Quando("eu faço uma requisição para o endpoint cadastrarProduto")
    public void eu_faço_uma_requisição_para_o_endpoint_cadastrar_produto() {
        produtoDomain = Fixture.ProdutoFixture.createProduto();
        Mockito.when(produtoService.cadastrarProduto(produtoDTO)).thenReturn(produtoDomain);
        produtoDomainResponseEntity = produtoController.cadastrarProduto(produtoDTO);
    }

    @Então("a resposta deve ter o status CREATED")
    public void a_resposta_deve_ter_o_status_created() {
        Assert.assertEquals(HttpStatus.CREATED, produtoDomainResponseEntity.getStatusCode());
    }

    @Então("o corpo da resposta deve conter os detalhes do produto")
    public void o_corpo_da_resposta_deve_conter_os_detalhes_do_produto() {
        Assert.assertEquals(UUID.fromString("875d85d9-d93e-4b89-9458-cb68684b1095"), produtoDomainResponseEntity.getBody().getId());
    }

    @Test
    public void teste01(){
        que_eu_tenho_um_produto_com_os_seguintes_detalhes();
        eu_faço_uma_requisição_para_o_endpoint_cadastrar_produto();
        a_resposta_deve_ter_o_status_created();
        o_corpo_da_resposta_deve_conter_os_detalhes_do_produto();
    }

}