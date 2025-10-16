package com.fiap.pj.infra.usuario.controller;


import com.fiap.pj.core.usuario.app.usecase.LoginUsuarioUseCase;
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

import static com.fiap.pj.core.usuario.util.factrory.UsuarioTestFactory.umLoginUsuarioCommand;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class LoginUsuarioControllerTest {

    @Mock
    private LoginUsuarioUseCase loginUsuarioUseCase;

    @InjectMocks
    private UsuarioLoginController usuarioLoginController;

    private MockMvc mock;

    @BeforeEach
    void setup() {
        mock = MockMvcBuilders.standaloneSetup(usuarioLoginController).build();
    }

    @Test
    void deveEfetuarLoginUsuario() throws Exception {
        mock.perform(post(
                TestUtils.buildURL(UsuarioLoginController.PATH))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtils.objectToJson(umLoginUsuarioCommand()))).andExpect(status().is2xxSuccessful());


    }

}
