package com.fiap.pj.infra.servico.controller;


import com.fiap.pj.core.servico.app.usecase.ExcluirServicoUserCase;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ExcluirServicoControllerTest {

    @Mock
    private ExcluirServicoUserCase excluirServicoUserCase;

    @InjectMocks
    private ServicoController servicoController;

    private MockMvc mock;

    @BeforeEach
    void setup() {
        mock = MockMvcBuilders.standaloneSetup(servicoController).build();
    }

    @Test
    void deveDeletarServico() throws Exception {

        mock.perform(delete(
                TestUtils.buildURL(ServicoController.PATH, UUID.randomUUID().toString()))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().is2xxSuccessful());
    }

}
