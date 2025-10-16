package com.fiap.pj.infra.pecasinsumo.controller;

import com.fiap.pj.core.pecainsumo.app.usecase.CriarPecaInsumoUseCase;
import com.fiap.pj.core.pecainsumo.app.usecase.command.CriarPecaInsumoCommand;
import com.fiap.pj.core.pecainsumo.util.factory.PecaInsumoTestFactory;
import com.fiap.pj.core.util.TestUtils;
import com.fiap.pj.infra.pecainsumo.controller.PecaInsumoController;
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

import static com.fiap.pj.core.pecainsumo.util.factory.PecaInsumoTestFactory.umCriarPecaInsumoCommand;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CriarPecaInsumoControllerTest {

    @Mock
    private CriarPecaInsumoUseCase criarPecaInsumoUseCase;

    @InjectMocks
    private PecaInsumoController pecaInsumoController;

    private MockMvc mock;

    @BeforeEach
    void setup() {
        mock = MockMvcBuilders.standaloneSetup(pecaInsumoController).build();
    }

    @Test
    void deveCriarPecaInsumo() throws Exception {

        Mockito.when(criarPecaInsumoUseCase.handle(Mockito.any(CriarPecaInsumoCommand.class))).thenReturn(PecaInsumoTestFactory.umPecaInsumo());

        mock.perform(post(
                TestUtils.buildURL(PecaInsumoController.PATH))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtils.objectToJson(umCriarPecaInsumoCommand()))).andExpect(status().is2xxSuccessful());

    }
} 