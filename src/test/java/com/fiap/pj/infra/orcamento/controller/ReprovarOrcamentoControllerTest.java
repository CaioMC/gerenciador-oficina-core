package com.fiap.pj.infra.orcamento.controller;


import com.fiap.pj.core.orcamento.app.usecase.ReprovarOrcamentoUseCase;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ReprovarOrcamentoControllerTest {

    @Mock
    private ReprovarOrcamentoUseCase reprovarOrcamentoUseCase;

    @InjectMocks
    private OrcamentoController orcamentoController;

    private MockMvc mock;

    @BeforeEach
    void setup() {
        mock = MockMvcBuilders.standaloneSetup(orcamentoController).build();
    }

    @Test
    void deveReprovarOrcamento() throws Exception {
        mock.perform(post(
                        TestUtils.buildURL(OrcamentoController.PATH, UUID.randomUUID().toString(), "reprovar"))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status()
                        .is2xxSuccessful());

    }

}
