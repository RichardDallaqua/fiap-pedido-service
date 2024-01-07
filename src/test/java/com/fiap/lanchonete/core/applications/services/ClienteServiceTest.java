package com.fiap.lanchonete.core.applications.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import com.fiap.lanchonete.commons.exception.NotFoundException;
import com.fiap.lanchonete.dataprovider.database.cliente.ClienteDataProvider;
import com.fiap.lanchonete.dataprovider.database.cliente.documents.ClienteDocument;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import com.fiap.lanchonete.dataprovider.database.cliente.repository.ClienteRepository;
import com.fiap.lanchonete.domain.ClienteDomain;
import com.fiap.lanchonete.fixture.Fixture;
import com.fiap.lanchonete.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;

@MockitoSettings
class ClienteServiceTest {

    @Mock
    private ClienteDataProvider clienteDataProvider;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    void testCadastrarCliente() {
        ClienteDomain cliente = Fixture.ClienteFixture.criarClientePadrao();

        clienteService.cadastrarCliente(cliente);

        verify(clienteDataProvider, times(1)).save(any());
    }

    @Test
    void testBuscarClientePorIdExistente() throws Exception {
        ClienteDomain cliente = Fixture.ClienteFixture.criarClientePadrao();

        when(clienteDataProvider.findById(any())).thenReturn(cliente);

        ClienteDomain result = clienteService.buscarClientePorId(cliente.getId());

        Assertions.assertEquals(cliente, result);
    }

    @Test
    void testBuscarClientePorIdNaoExistente() {
        UUID clienteId = UUID.randomUUID();

        when(clienteDataProvider.findById(clienteId)).thenThrow(NotFoundException.class);

        Assertions.assertThrows(Exception.class, () -> clienteService.buscarClientePorId(clienteId));
    }
}