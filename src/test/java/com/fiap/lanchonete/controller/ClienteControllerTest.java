package com.fiap.lanchonete.controller;

import com.fiap.lanchonete.controller.dto.ClienteDTO;
import com.fiap.lanchonete.domain.CPFDomain;
import com.fiap.lanchonete.domain.ClienteDomain;
import com.fiap.lanchonete.fixture.Fixture;
import com.fiap.lanchonete.services.ClienteService;
import io.cucumber.java.it.Quando;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.junit.platform.engine.Cucumber;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@MockitoSettings(strictness = Strictness.LENIENT)
class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;
    @InjectMocks
    private ClienteController controller;
    private UUID clientId;
    private ResponseEntity<ClienteDomain> responseEntity;
    private ClienteDTO clienteDTO;

    @Dado("um cliente com ID 4a8bc9e8-ab42-49eb-8eb3-8bcdb969a378")
    public void um_cliente_com_id_4a8bc9e8_ab42_49eb_8eb3_8bcdb969a378(UUID id) {
        clientId = id;
    }

    @Quando("o cliente solicita informações para esse ID")
    public void o_cliente_solicita_informações_para_esse_id() {
        when(clienteService.buscarClientePorId(clientId)).thenReturn(Fixture.ClienteFixture.criarClientePadrao());
        responseEntity = controller.obterClientePorId(clientId);

    }

    @Então("o código de status da resposta deve ser {int}")
    public void o_código_de_status_da_resposta_deve_ser(Integer expectedStatus) {
        assertEquals(expectedStatus, responseEntity.getStatusCode().value());

    }

    @Então("o corpo da resposta deve conter o nome do cliente John Doe")
    public void o_corpo_da_resposta_deve_conter_o_nome_do_cliente_john_doe() {
        assertEquals("John Doe", responseEntity.getBody().getNome());
    }

    @Test
    public void teste01() {
        um_cliente_com_id_4a8bc9e8_ab42_49eb_8eb3_8bcdb969a378(UUID.fromString("4a8bc9e8-ab42-49eb-8eb3-8bcdb969a378"));
        o_cliente_solicita_informações_para_esse_id();
        o_código_de_status_da_resposta_deve_ser(200);
        o_corpo_da_resposta_deve_conter_o_nome_do_cliente_john_doe();
    }

    @Dado("um cliente com os seguintes detalhes:")
    public void um_cliente_com_os_seguintes_detalhes() {
        UUID ramdonUUID = UUID.randomUUID();
        LocalDate hoje = LocalDate.now();
        clienteDTO = ClienteDTO.builder()
                .nome("Jane Smith")
                .cpf("123.456.789-09")
                .telefone("(555) 123-4567")
                .build();
        ClienteDomain clienteDomain = ClienteDomain.builder().id(ramdonUUID)
                .nome(clienteDTO.getNome()).cpf(new CPFDomain(clienteDTO.getCpf()))
                .telefone(clienteDTO.getTelefone()).dataCadastro(hoje).build();
        when(clienteService.cadastrarCliente(clienteDomain))
                .thenReturn(clienteDomain);
        responseEntity = controller.criarCliente(clienteDTO);
    }

    @Test
    public void teste02() {
        um_cliente_com_os_seguintes_detalhes();
        o_código_de_status_da_resposta_deve_ser(201);
    }
}