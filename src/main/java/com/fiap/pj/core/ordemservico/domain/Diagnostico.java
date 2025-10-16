package com.fiap.pj.core.ordemservico.domain;

import com.fiap.pj.core.util.DateTimeUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Diagnostico {

    private UUID id;
    private String descricao;
    private UUID ordemServicoId;
    private ZonedDateTime dataCriacao;

    public Diagnostico(
            UUID ordemServicoId,
            String descricao
    ) {
        this.id = UUID.randomUUID();
        this.ordemServicoId = ordemServicoId;
        this.descricao = descricao;
        this.dataCriacao = DateTimeUtils.getNow();
    }
}