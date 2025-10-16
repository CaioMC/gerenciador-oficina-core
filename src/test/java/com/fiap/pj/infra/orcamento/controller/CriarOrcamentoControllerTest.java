package com.fiap.pj.infra.orcamento.controller;


import com.fiap.pj.core.orcamento.app.usecase.CriarOrcamentoUseCase;
import com.fiap.pj.core.orcamento.app.usecase.command.CriarOrcamentoCommand;
import com.fiap.pj.core.orcamento.util.factory.OrcamentoTestFactory;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CriarOrcamentoControllerTest {

    @Mock
    private CriarOrcamentoUseCase criarOrcamentoUseCase;

    @InjectMocks
    private OrcamentoController orcamentoController;

    private MockMvc mock;

    @BeforeEach
    void setup() {
        mock = MockMvcBuilders.standaloneSetup(orcamentoController).build();
    }

    @Test
    void deveCriarOrcamento() throws Exception {

        Mockito.when(criarOrcamentoUseCase.handle(Mockito.any(CriarOrcamentoCommand.class))).thenReturn(OrcamentoTestFactory.umOrcamento());

        mock.perform(post(
                        TestUtils.buildURL(OrcamentoController.PATH))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtils.objectToJson(OrcamentoTestFactory.umCriarOrcamentoCommand())))
                .andExpect(status()
                        .is2xxSuccessful());

    }

}
