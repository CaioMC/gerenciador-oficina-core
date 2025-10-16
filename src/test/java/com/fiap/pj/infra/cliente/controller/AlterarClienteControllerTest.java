package com.fiap.pj.infra.cliente.controller;


import com.fiap.pj.core.cliente.app.usecase.AlterarClienteUserCase;
import com.fiap.pj.core.cliente.util.factory.ClienteTestFactory;
import com.fiap.pj.core.util.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static com.fiap.pj.core.cliente.util.factory.ClienteTestFactory.umAlterarClienteCommand;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AlterarClienteControllerTest {

    @Mock
    private AlterarClienteUserCase alterarClienteUserCase;

    @InjectMocks
    private ClienteController clienteController;

    private MockMvc mock;

    @BeforeEach
    void setup() {
        mock = MockMvcBuilders.standaloneSetup(clienteController).build();
    }

    @Test
    void deveAlterarCliente() throws Exception {

        mock.perform(put(
                TestUtils.buildURL(ClienteController.PATH, UUID.randomUUID().toString()))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtils.objectToJson(umAlterarClienteCommand(ClienteTestFactory.ID)))).andExpect(status().is2xxSuccessful());
    }


}
