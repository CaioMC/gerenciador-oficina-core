package com.fiap.pj.infra.servico.controller;


import com.fiap.pj.core.servico.util.factory.ServicoTestFactory;
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


@Sql(scripts = {"classpath:db/it/servicos/create_servicos.sql"
}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@IntegrationTest
class ListarServicoControllerIT {

    @Autowired
    private MockMvc mock;

    @Test
    @WithMockUser(username = "testuser", roles = {"ADMIN"})
    void deveListarServicos() throws Exception {
        mock.perform(get(
                        TestUtils.buildURL(ServicoController.PATH)).param("descricao", "troca").param("ativo", "true")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.hasNext", is(false)))
                .andExpect(jsonPath("$.items[0].id", is("84c052e5-3aa5-465b-9e14-6551e4ba1a65")))
                .andExpect(jsonPath("$.items[0].descricao", is(ServicoTestFactory.DESCRICAO)))
                .andExpect(jsonPath("$.items[0].observacao", is(ServicoTestFactory.OBSERVACAO)))
                .andExpect(jsonPath("$.items[0].ativo", is(true)));
    }

}
