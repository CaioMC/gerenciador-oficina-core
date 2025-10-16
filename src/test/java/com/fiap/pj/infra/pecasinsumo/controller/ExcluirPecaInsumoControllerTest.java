package com.fiap.pj.infra.pecasinsumo.controller;

import com.fiap.pj.core.pecainsumo.app.usecase.ExcluirPecaInsumoUseCase;
import com.fiap.pj.core.util.TestUtils;
import com.fiap.pj.infra.pecainsumo.controller.PecaInsumoController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ExcluirPecaInsumoControllerTest {

    @Mock
    private ExcluirPecaInsumoUseCase excluirPecaInsumoUseCase;

    @InjectMocks
    private PecaInsumoController pecaInsumoController;

    private MockMvc mock;

    @BeforeEach
    void setup() {
        mock = MockMvcBuilders.standaloneSetup(pecaInsumoController).build();
    }

    @Test
    void deveExcluirPecaInsumo() throws Exception {
        var id = UUID.randomUUID();

        mock.perform(delete(
                        TestUtils.buildURL(PecaInsumoController.PATH + "/" + id)))
                .andExpect(status().isOk());
    }
} 