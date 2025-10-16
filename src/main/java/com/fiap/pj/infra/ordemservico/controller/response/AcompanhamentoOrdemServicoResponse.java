package com.fiap.pj.infra.ordemservico.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus;
import com.fiap.pj.infra.ordemservico.controller.response.OrdemServicoResponse.UsuarioResponse;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.UUID;

@JsonPropertyOrder({"id", "acompanhamentos"})
public interface AcompanhamentoOrdemServicoResponse {

    UUID getId();

    @JsonProperty("acompanhamentos")
    Collection<SituacaoResponse> getHistoricoSituacao();

    @JsonPropertyOrder({"id", "status", "usuario", "dataCriacao"})
    interface SituacaoResponse {
        UUID getId();

        OrdemServicoStatus getStatus();

        UsuarioResponse getUsuario();

        ZonedDateTime getDataCriacao();

    }

}