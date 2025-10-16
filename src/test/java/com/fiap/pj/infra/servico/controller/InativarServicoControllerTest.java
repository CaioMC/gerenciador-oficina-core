package com.fiap.pj.infra.servico.controller;


import com.fiap.pj.core.servico.app.usecase.InativarServicoUserCase;
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

import static com.fiap.pj.core.cliente.util.factory.ClienteTestFactory.umCriarClienteCommand;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class InativarServicoControllerTest {

    @Mock
    private InativarServicoUserCase inativarServicoUserCase;

    @InjectMocks
    private ServicoController servicoController;

    private MockMvc mock;

    @BeforeEach
    void setup() {
        mock = MockMvcBuilders.standaloneSetup(servicoController).build();
    }

    @Test
    void deveInativarServico() throws Exception {

        mock.perform(post(
                TestUtils.buildURL(ServicoController.PATH, UUID.randomUUID().toString(), "inativar"))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtils.objectToJson(umCriarClienteCommand()))).andExpect(status().is2xxSuccessful());
    }

}
