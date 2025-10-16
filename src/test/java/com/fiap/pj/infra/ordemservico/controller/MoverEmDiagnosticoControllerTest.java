package com.fiap.pj.infra.ordemservico.controller;


import com.fiap.pj.core.ordemservico.app.usecase.MoverEmDiagnosticoUseCase;
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

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class MoverEmDiagnosticoControllerTest {

    @Mock
    private MoverEmDiagnosticoUseCase moverEmDiagnosticoUseCase;

    @InjectMocks
    private OrdemServicoController ordemServicoController;

    private MockMvc mock;

    @BeforeEach
    void setup() {
        mock = MockMvcBuilders.standaloneSetup(ordemServicoController).build();
    }

    @Test
    void deveMoverEmDiagnostico() throws Exception {
        mock.perform(post(
                TestUtils.buildURL(OrdemServicoController.PATH, UUID.randomUUID().toString(), "diagnosticar"))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().is2xxSuccessful());


        Mockito.verify(moverEmDiagnosticoUseCase).handle(Mockito.any(UUID.class));

    }

}
