package com.fiap.pj.infra.servico.controller;


import com.fiap.pj.core.servico.app.usecase.AlterarServicoUseCase;
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

import static com.fiap.pj.core.servico.util.factory.ServicoTestFactory.umCriarServicoCommand;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AlterarServicoControllerTest {

    @Mock
    private AlterarServicoUseCase alterarServicoUseCase;

    @InjectMocks
    private ServicoController serviceController;

    private MockMvc mock;

    @BeforeEach
    void setup() {
        mock = MockMvcBuilders.standaloneSetup(serviceController).build();
    }

    @Test
    void deveAlterarServico() throws Exception {

        mock.perform(put(
                TestUtils.buildURL(ServicoController.PATH, UUID.randomUUID().toString()))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtils.objectToJson(umCriarServicoCommand()))).andExpect(status().is2xxSuccessful());

    }

}
