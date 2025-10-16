package com.fiap.pj.infra.usuario.controller;


import com.fiap.pj.core.util.TestUtils;
import com.fiap.pj.infra.helpers.JwtUtil;
import com.fiap.pj.util.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Sql(scripts = {"classpath:db/it/usuarios/create_usuarios.sql"
}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@IntegrationTest
class LIstarlUsuarioControllerIT {

    @Autowired
    private MockMvc mock;

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    @WithMockUser(username = "testuser", roles = {"ADMIN"})
    void deveListarUsuarios() throws Exception {
        mock.perform(get(
                        TestUtils.buildURL(UsuarioController.PATH)).param("nome", "urso").param("ativo", "true")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.hasNext", is(false)))
                .andExpect(jsonPath("$.items[0].id", is("ace50297-1785-4a7d-ae6d-8ec2dc450af6")))
                .andExpect(jsonPath("$.items[0].nome", is("Urso")))
                .andExpect(jsonPath("$.items[0].sobreNome", is("Teddy")))
                .andExpect(jsonPath("$.items[0].email", is("urso.teddy@gmail.com")))
                .andExpect(jsonPath("$.items[0].ativo", is(true)));
    }

}
