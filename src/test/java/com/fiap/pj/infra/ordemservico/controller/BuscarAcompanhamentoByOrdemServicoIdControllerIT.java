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


@Sql(scripts = {"classpath:db/it/ordens-servico/create_situacoes_ordens_servico.sql"
}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@IntegrationTest
class BuscarAcompanhamentoByOrdemServicoIdControllerIT {

    @Autowired
    private MockMvc mock;

    @Test
    @WithMockUser(username = "testuser", roles = {"ADMIN"})
    void deveListarOrdemServicoPorId() throws Exception {
        mock.perform(get(
                        TestUtils.buildURL(OrdemServicoController.PATH, "01bd50f7-2c6d-45f5-bee2-cea5b7e0be9a", "acompanhamentos"))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("01bd50f7-2c6d-45f5-bee2-cea5b7e0be9a")))
                .andExpect(jsonPath("$.acompanhamentos[0].id", is("ddcffd69-cf96-4260-a56f-573cba69899c")))
                .andExpect(jsonPath("$.acompanhamentos[0].status", is(OrdemServicoStatus.ENTREGUE.name())))
                .andExpect(jsonPath("$.acompanhamentos[0].dataCriacao", is("2025-08-13T13:06:50Z")))
                .andExpect(jsonPath("$.acompanhamentos[0].usuario.id", is("37255ba6-ca9b-4024-af66-d2135eb7ffca")))
                .andExpect(jsonPath("$.acompanhamentos[0].usuario.nome", is("Florinda")))
                .andExpect(jsonPath("$.acompanhamentos[1].id", is("a5eeb67b-4e9c-4c31-bfab-e1abc3f54e94")))
                .andExpect(jsonPath("$.acompanhamentos[1].status", is(OrdemServicoStatus.AGUARDANDO_RETIRADA.name())))
                .andExpect(jsonPath("$.acompanhamentos[1].dataCriacao", is("2025-08-12T15:06:50Z")))
                .andExpect(jsonPath("$.acompanhamentos[1].usuario.id", is("37255ba6-ca9b-4024-af66-d2135eb7ffca")))
                .andExpect(jsonPath("$.acompanhamentos[1].usuario.nome", is("Florinda")))
                .andExpect(jsonPath("$.acompanhamentos[2].id", is("99a42b05-f88b-40b4-be95-88494e661fc3")))
                .andExpect(jsonPath("$.acompanhamentos[2].status", is(OrdemServicoStatus.FINALIZADA.name())))
                .andExpect(jsonPath("$.acompanhamentos[2].dataCriacao", is("2025-08-12T13:06:50Z")))
                .andExpect(jsonPath("$.acompanhamentos[2].usuario.id", is("ace50297-1785-4a7d-ae6d-8ec2dc450af6")))
                .andExpect(jsonPath("$.acompanhamentos[2].usuario.nome", is("Severino")));


    }

}