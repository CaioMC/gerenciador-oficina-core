package com.fiap.pj.infra.pecasinsumo.controller;

import com.fiap.pj.core.pecainsumo.app.usecase.AlterarPecaInsumoUseCase;
import com.fiap.pj.core.util.TestUtils;
import com.fiap.pj.infra.pecainsumo.controller.PecaInsumoController;
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

import static com.fiap.pj.core.pecainsumo.util.factory.PecaInsumoTestFactory.umAlterarPecaInsumoCommand;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AlterarPecaInsumoControllerTest {

    @Mock
    private AlterarPecaInsumoUseCase alterarPecaInsumoUseCase;

    @InjectMocks
    private PecaInsumoController pecaInsumoController;

    private MockMvc mock;

    @BeforeEach
    void setup() {
        mock = MockMvcBuilders.standaloneSetup(pecaInsumoController).build();
    }

    @Test
    void deveAlterarPecaInsumo() throws Exception {
        var id = UUID.randomUUID();

        mock.perform(put(
                TestUtils.buildURL(PecaInsumoController.PATH + "/" + id))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtils.objectToJson(umAlterarPecaInsumoCommand(id)))).andExpect(status().isOk());

    }
} 