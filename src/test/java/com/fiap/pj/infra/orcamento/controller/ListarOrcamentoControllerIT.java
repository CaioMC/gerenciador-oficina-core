package com.fiap.pj.infra.orcamento.controller;


import com.fiap.pj.core.orcamento.domain.enums.OrcamentoStatus;
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


@Sql(scripts = {"classpath:db/it/orcamentos/create_orcamentos.sql"
}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@IntegrationTest
class ListarOrcamentoControllerIT {

    @Autowired
    private MockMvc mock;

    @Test
    @WithMockUser(username = "testuser", roles = {"ADMIN"})
    void deveListarOrcamentos() throws Exception {
        mock.perform(get(
                        TestUtils.buildURL(OrcamentoController.PATH)).param("clienteId", "113e83b4-02e3-4059-8498-f4beafbb5ed9")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.hasNext", is(false)))
                .andExpect(jsonPath("$.items[0].id", is("3ccaf177-2aa0-4f11-b681-431e1b2323b0")))
                .andExpect(jsonPath("$.items[0].descricao", is("XPTO")))
                .andExpect(jsonPath("$.items[0].cliente.id", is("113e83b4-02e3-4059-8498-f4beafbb5ed9")))
                .andExpect(jsonPath("$.items[0].cliente.nome", is("Hakuna")))
                .andExpect(jsonPath("$.items[0].veiculo.id", is("e30facee-4403-4494-b506-89feddf52ab1")))
                .andExpect(jsonPath("$.items[0].veiculo.placa", is("CUA1H50")))
                .andExpect(jsonPath("$.items[0].veiculo.modelo", is("FIAT 147")))
                .andExpect(jsonPath("$.items[0].veiculo.marca", is("FIAT")))
                .andExpect(jsonPath("$.items[0].hodometro", is(11000)))
                .andExpect(jsonPath("$.items[0].status", is(OrcamentoStatus.AGUARDANDO_APROVACAO.name())))
                .andExpect(jsonPath("$.items[0].servicos[0].id", is("7422d4fd-ea12-415a-98af-810c803ab35c")))
                .andExpect(jsonPath("$.items[0].servicos[0].descricao", is("Troca de Óleo")))
                .andExpect(jsonPath("$.items[0].servicos[0].valorUnitario", is(69)))
                .andExpect(jsonPath("$.items[0].servicos[0].quantidade", is(1)))
                .andExpect(jsonPath("$.items[0].pecasInsumos[0].id", is("afe064ba-de28-4e67-9b7a-daf2586b95fe")))
                .andExpect(jsonPath("$.items[0].pecasInsumos[0].descricao", is("Óleo do Motor")))
                .andExpect(jsonPath("$.items[0].pecasInsumos[0].valorUnitario", is(45.50)))
                .andExpect(jsonPath("$.items[0].pecasInsumos[0].quantidade", is(1)))
                .andExpect(jsonPath("$.items[0].valorTotal", is(114.5000)));
    }
}