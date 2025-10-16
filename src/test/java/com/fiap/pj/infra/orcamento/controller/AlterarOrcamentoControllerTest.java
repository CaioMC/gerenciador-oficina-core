package com.fiap.pj.infra.orcamento.controller;


import com.fiap.pj.core.orcamento.app.usecase.AlterarOrcamentoUseCase;
import com.fiap.pj.core.orcamento.util.factory.OrcamentoTestFactory;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AlterarOrcamentoControllerTest {

    @Mock
    private AlterarOrcamentoUseCase alterarOrcamentoUseCase;

    @InjectMocks
    private OrcamentoController orcamentoController;

    private MockMvc mock;

    @BeforeEach
    void setup() {
        mock = MockMvcBuilders.standaloneSetup(orcamentoController).build();
    }

    @Test
    void deveAlterarOrcamento() throws Exception {

        mock.perform(put(
                        TestUtils.buildURL(OrcamentoController.PATH, OrcamentoTestFactory.ID.toString()))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtils.objectToJson(OrcamentoTestFactory.umAlterarOrcamentoCommand(OrcamentoTestFactory.ID))))
                .andExpect(status().is2xxSuccessful());

    }

}
