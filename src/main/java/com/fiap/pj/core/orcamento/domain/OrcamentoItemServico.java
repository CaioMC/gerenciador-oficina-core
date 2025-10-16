package com.fiap.pj.core.orcamento.domain;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

import static java.util.Objects.requireNonNull;


@Getter
public class OrcamentoItemServico {

    private UUID id;
    private UUID servicoId;
    private UUID orcamentoId;
    private String descricao;
    private BigDecimal valorUnitario;
    private Integer quantidade;

    @Builder
    public OrcamentoItemServico(UUID id, UUID servicoId, UUID orcamentoId, String descricao, BigDecimal valorUnitario, Integer quantidade) {
        this.id = requireNonNull(id);
        this.servicoId = requireNonNull(servicoId);
        this.orcamentoId = requireNonNull(orcamentoId);
        this.descricao = requireNonNull(descricao);
        this.valorUnitario = valorUnitario;
        this.quantidade = quantidade;
    }
}
