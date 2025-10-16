package com.fiap.pj.infra.usuario.controller;


import com.fiap.pj.core.usuario.app.usecase.AlterarUsuarioUseCase;
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

import static com.fiap.pj.core.usuario.util.factrory.UsuarioTestFactory.umCriarUsuarioCommand;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AlterarUsuarioControllerTest {

    @Mock
    private AlterarUsuarioUseCase alterarUsuarioUseCase;

    @InjectMocks
    private UsuarioController userController;

    private MockMvc mock;

    @BeforeEach
    void setup() {
        mock = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void deveAlterarUser() throws Exception {

        mock.perform(put(
                TestUtils.buildURL(UsuarioController.PATH, UUID.randomUUID().toString()))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtils.objectToJson(umCriarUsuarioCommand()))).andExpect(status().is2xxSuccessful());

    }

}
