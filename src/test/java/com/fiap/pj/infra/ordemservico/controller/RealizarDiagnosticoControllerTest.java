package com.fiap.pj.infra.ordemservico.controller;


import com.fiap.pj.core.ordemservico.app.usecase.RealizarDiagnosticoUseCase;
import com.fiap.pj.core.ordemservico.app.usecase.command.RealizarDiagnosticoOrdemServicoCommand;
import com.fiap.pj.core.ordemservico.util.factory.OrdemServicoTestFactory;
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
class RealizarDiagnosticoControllerTest {

    @Mock
    private RealizarDiagnosticoUseCase realizarDiagnosticoUseCase;

    @InjectMocks
    private OrdemServicoController ordemServicoController;

    private MockMvc mock;

    @BeforeEach
    void setup() {
        mock = MockMvcBuilders.standaloneSetup(ordemServicoController).build();
    }

    @Test
    void deveRealizarUmDiagnostico() throws Exception {

        mock.perform(post(
                TestUtils.buildURL(OrdemServicoController.PATH, UUID.randomUUID().toString(), "ralizar-diagnostico"))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtils.objectToJson(OrdemServicoTestFactory.umRealizarDiagnosticoOrdemServicoCommand()))).andExpect(status().is2xxSuccessful());


        Mockito.verify(realizarDiagnosticoUseCase).handle(Mockito.any(RealizarDiagnosticoOrdemServicoCommand.class));

    }

}
