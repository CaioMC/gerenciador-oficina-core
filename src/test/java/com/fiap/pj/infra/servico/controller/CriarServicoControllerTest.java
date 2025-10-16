package com.fiap.pj.infra.servico.controller;


import com.fiap.pj.core.servico.app.usecase.CriarServicoUseCase;
import com.fiap.pj.core.servico.app.usecase.command.CriarServicoCommand;
import com.fiap.pj.core.servico.util.factory.ServicoTestFactory;
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

import static com.fiap.pj.core.servico.util.factory.ServicoTestFactory.umCriarServicoCommand;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CriarServicoControllerTest {

    @Mock
    private CriarServicoUseCase criarServicoUseCase;

    @InjectMocks
    private ServicoController serviceController;

    private MockMvc mock;

    @BeforeEach
    void setup() {
        mock = MockMvcBuilders.standaloneSetup(serviceController).build();
    }

    @Test
    void deveCriarServico() throws Exception {

        Mockito.when(criarServicoUseCase.handle(Mockito.any(CriarServicoCommand.class))).thenReturn(ServicoTestFactory.umServico());

        mock.perform(post(
                TestUtils.buildURL(ServicoController.PATH))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtils.objectToJson(umCriarServicoCommand()))).andExpect(status().is2xxSuccessful());

    }

}
