package com.fiap.pj.infra.cliente.controller;


import com.fiap.pj.core.cliente.util.factory.ClienteTestFactory;
import com.fiap.pj.core.util.TestUtils;
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


@Sql(scripts = {"classpath:db/it/clientes/create_clientes.sql"
}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@IntegrationTest
class ListarClienteControllerIT {

    @Autowired
    private MockMvc mock;

    @Test
    @WithMockUser(username = "testuser", roles = {"ADMIN"})
    void deveListarClientesAtivo() throws Exception {
        mock.perform(get(
                        TestUtils.buildURL(ClienteController.PATH)).param("nome", "haku").param("ativo", "true")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.hasNext", is(false)))
                .andExpect(jsonPath("$.items[0].id", is("113e83b4-02e3-4059-8498-f4beafbb5ed9")))
                .andExpect(jsonPath("$.items[0].nome", is(ClienteTestFactory.NOME)))
                .andExpect(jsonPath("$.items[0].email", is(ClienteTestFactory.E_MAIL)))
                .andExpect(jsonPath("$.items[0].telefone", is(ClienteTestFactory.TELEFONE)))
                .andExpect(jsonPath("$.items[0].endereco", is(ClienteTestFactory.ENDERECO)))
                .andExpect(jsonPath("$.items[0].documentoIdentificacao.numero", is(ClienteTestFactory.NUMERO_DOCUMENTO)))
                .andExpect(jsonPath("$.items[0].ativo", is(true)));
    }

}
