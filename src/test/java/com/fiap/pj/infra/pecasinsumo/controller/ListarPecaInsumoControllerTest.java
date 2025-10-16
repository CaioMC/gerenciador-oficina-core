package com.fiap.pj.infra.pecasinsumo.controller;

import com.fiap.pj.core.pecainsumo.app.usecase.ListarPecaInsumoUseCase;
import com.fiap.pj.core.util.TestUtils;
import com.fiap.pj.infra.pecainsumo.controller.PecaInsumoController;
import com.fiap.pj.infra.pecainsumo.controller.request.ListarPecaInsumoRequest;
import com.fiap.pj.infra.pecainsumo.controller.response.PecaInsumoResponse;
import com.fiap.pj.infra.sk.api.Slice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ListarPecaInsumoControllerTest {

    @Mock
    private ListarPecaInsumoUseCase listarPecaInsumoUseCase;

    @InjectMocks
    private PecaInsumoController pecaInsumoController;

    private MockMvc mock;

    @BeforeEach
    void setup() {
        mock = MockMvcBuilders.standaloneSetup(pecaInsumoController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @Test
    @WithMockUser(username = "testuser", roles = {"ADMIN"})
    void deveListarPecasInsumos() throws Exception {
        new ListarPecaInsumoRequest("fusca", "óleo", null, PageRequest.of(0, 10));

        Mockito.when(listarPecaInsumoUseCase.handle(Mockito.any(ListarPecaInsumoRequest.class)))
                .thenReturn(new Slice<PecaInsumoResponse>(false, List.of()));

        mock.perform(get(TestUtils.buildURL(PecaInsumoController.PATH))
                        .param("modeloVeiculo", "fusca")
                        .param("descricao", "óleo")
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }
} 