package com.fiap.pj.infra.ordemservico.controller;


import com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus;
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


@Sql(scripts = {"classpath:db/it/ordens-servico/create_ordens_servico.sql"
}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@IntegrationTest
class ListarOrdemServicoControllerIT {

    @Autowired
    private MockMvc mock;

    @Test
    @WithMockUser(username = "testuser", roles = {"ADMIN"})
    void deveListarOrdemServicoPorId() throws Exception {
        mock.perform(get(
                        TestUtils.buildURL(OrdemServicoController.PATH)).param("id", "da088a1a-67ce-463b-a79b-a4ab28fe44ae")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.hasNext", is(false)))
                .andExpect(jsonPath("$.items[0].id", is("da088a1a-67ce-463b-a79b-a4ab28fe44ae")))
                .andExpect(jsonPath("$.items[0].cliente.id", is("113e83b4-02e3-4059-8498-f4beafbb5ed9")))
                .andExpect(jsonPath("$.items[0].cliente.nome", is("Hakuna")))
                .andExpect(jsonPath("$.items[0].veiculo.id", is("e30facee-4403-4494-b506-89feddf52ab1")))
                .andExpect(jsonPath("$.items[0].veiculo.placa", is("CUA1H50")))
                .andExpect(jsonPath("$.items[0].veiculo.modelo", is("FIAT 147")))
                .andExpect(jsonPath("$.items[0].veiculo.marca", is("FIAT")))
                .andExpect(jsonPath("$.items[0].usuario.id", is("ace50297-1785-4a7d-ae6d-8ec2dc450af6")))
                .andExpect(jsonPath("$.items[0].usuario.nome", is("Severino")))
                .andExpect(jsonPath("$.items[0].diagnostico.id", is("a52a365c-bbe5-4993-b1af-5dcc6ac125d0")))
                .andExpect(jsonPath("$.items[0].diagnostico.descricao", is("Amortecedor vazando")))
                .andExpect(jsonPath("$.items[0].status", is(OrdemServicoStatus.EM_EXECUCAO.name())));

    }

    @Test
    @WithMockUser(username = "testuser", roles = {"ADMIN"})
    void deveListarOrdemServico() throws Exception {
        mock.perform(get(
                        TestUtils.buildURL(OrdemServicoController.PATH)).param("clienteId", "113e83b4-02e3-4059-8498-f4beafbb5ed9")
                        .param("usuarioId", "ace50297-1785-4a7d-ae6d-8ec2dc450af6")
                        .param("veiculoId", "e30facee-4403-4494-b506-89feddf52ab1")
                        .param("status", "EM_EXECUCAO")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.hasNext", is(false)))
                .andExpect(jsonPath("$.items[0].id", is("da088a1a-67ce-463b-a79b-a4ab28fe44ae")))
                .andExpect(jsonPath("$.items[0].cliente.id", is("113e83b4-02e3-4059-8498-f4beafbb5ed9")))
                .andExpect(jsonPath("$.items[0].cliente.nome", is("Hakuna")))
                .andExpect(jsonPath("$.items[0].veiculo.id", is("e30facee-4403-4494-b506-89feddf52ab1")))
                .andExpect(jsonPath("$.items[0].veiculo.placa", is("CUA1H50")))
                .andExpect(jsonPath("$.items[0].veiculo.modelo", is("FIAT 147")))
                .andExpect(jsonPath("$.items[0].veiculo.marca", is("FIAT")))
                .andExpect(jsonPath("$.items[0].usuario.id", is("ace50297-1785-4a7d-ae6d-8ec2dc450af6")))
                .andExpect(jsonPath("$.items[0].usuario.nome", is("Severino")))
                .andExpect(jsonPath("$.items[0].diagnostico.id", is("a52a365c-bbe5-4993-b1af-5dcc6ac125d0")))
                .andExpect(jsonPath("$.items[0].diagnostico.descricao", is("Amortecedor vazando")))
                .andExpect(jsonPath("$.items[0].status", is(OrdemServicoStatus.EM_EXECUCAO.name())));

    }
}