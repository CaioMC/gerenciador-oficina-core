package com.fiap.pj.core.ordemservico.domain;


import com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus;
import com.fiap.pj.core.util.DateTimeUtils;
import com.fiap.pj.infra.util.security.SecurityContextUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class SituacaoOrdemServico {

    private UUID id;
    private OrdemServicoStatus status;
    private UUID usuarioId;
    private UUID ordemServicoId;
    private ZonedDateTime dataCriacao;

    public SituacaoOrdemServico(OrdemServicoStatus status, UUID ordemServicoId) {
        this.id = UUID.randomUUID();
        this.status = status;
        this.usuarioId = SecurityContextUtils.getUsuarioId();
        this.ordemServicoId = ordemServicoId;
        this.dataCriacao = DateTimeUtils.getNow();
    }

}
