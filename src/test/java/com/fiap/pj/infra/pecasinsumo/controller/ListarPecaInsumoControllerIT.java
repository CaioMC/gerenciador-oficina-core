package com.fiap.pj.infra.pecasinsumo.controller;

import com.fiap.pj.core.util.TestUtils;
import com.fiap.pj.infra.pecainsumo.controller.PecaInsumoController;
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

@Sql(scripts = {"classpath:db/it/pecas/create_pecas_insumos.sql"
}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@IntegrationTest
class ListarPecaInsumoControllerIT {

    @Autowired
    private MockMvc mock;

    @Test
    @WithMockUser(username = "testuser", roles = {"ADMIN"})
    void deveListarPecasInsumos() throws Exception {
        mock.perform(get(
                        TestUtils.buildURL(PecaInsumoController.PATH)).param("descricao", "Óleo ").param("modeloVeiculo", "fusca")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.hasNext", is(false)))
                .andExpect(jsonPath("$.items[0].id", is("ace50297-1785-4a7d-ae6d-8ec2dc450af6")))
                .andExpect(jsonPath("$.items[0].descricao", is("Óleo do Motor")))
                .andExpect(jsonPath("$.items[0].modeloVeiculo", is("fusca 1600")))
                .andExpect(jsonPath("$.items[0].valorUnitario", is(45.50)))
                .andExpect(jsonPath("$.items[0].quantidadeEstoque", is(10)))
                .andExpect(jsonPath("$.items[0].quantidadeMinimoEstoque", is(5)));
    }

    @Test
    @WithMockUser(username = "testuser", roles = {"ADMIN"})
    void deveListarPecasInsumosComEstoqueBaixo() throws Exception {
        mock.perform(get(
                        TestUtils.buildURL(PecaInsumoController.PATH)).param("estoqueBaixo", "true")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.hasNext", is(false)))
                .andExpect(jsonPath("$.items[0].id", is("bce50297-1785-4a7d-ae6d-8ec2dc450af7")))
                .andExpect(jsonPath("$.items[0].descricao", is("Filtro de Ar")))
                .andExpect(jsonPath("$.items[0].modeloVeiculo", is("fiat 147")))
                .andExpect(jsonPath("$.items[0].valorUnitario", is(25.00)))
                .andExpect(jsonPath("$.items[0].quantidadeEstoque", is(3)))
                .andExpect(jsonPath("$.items[0].quantidadeMinimoEstoque", is(5)));
    }
} 