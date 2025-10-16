package com.fiap.pj.infra.cliente.controller;


import com.fiap.pj.core.cliente.app.usecase.CriarClienteUserCase;
import com.fiap.pj.core.cliente.app.usecase.command.CriarClienteCommand;
import com.fiap.pj.core.cliente.util.factory.ClienteTestFactory;
import com.fiap.pj.core.util.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.fiap.pj.core.cliente.util.factory.ClienteTestFactory.umCriarClienteCNPJCommand;
import static com.fiap.pj.core.cliente.util.factory.ClienteTestFactory.umCriarClienteCommand;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CriarControllerTest {

    @Mock
    private CriarClienteUserCase criarClienteUserCase;

    @InjectMocks
    private ClienteController clienteController;

    private MockMvc mock;

    @BeforeEach
    void setup() {
        mock = MockMvcBuilders.standaloneSetup(clienteController).build();
    }

    @Test
    void deveCriarCliente() throws Exception {

        Mockito.when(criarClienteUserCase.handle(Mockito.any(CriarClienteCommand.class))).thenReturn(ClienteTestFactory.umCliente());

        mock.perform(post(
                TestUtils.buildURL(ClienteController.PATH))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtils.objectToJson(umCriarClienteCommand()))).andExpect(status().is2xxSuccessful());
    }

    @Test
    void deveCriarClienteComCnpj() throws Exception {
        Mockito.when(criarClienteUserCase.handle(Mockito.any(CriarClienteCommand.class))).thenReturn(ClienteTestFactory.umClienteCNPJ());
        mock.perform(post(
                TestUtils.buildURL(ClienteController.PATH))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtils.objectToJson(umCriarClienteCNPJCommand()))).andExpect(status().is2xxSuccessful());

    }

}
