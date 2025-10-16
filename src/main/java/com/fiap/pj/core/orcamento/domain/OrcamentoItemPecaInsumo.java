package com.fiap.pj.core.orcamento.domain;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

import static java.util.Objects.requireNonNull;


@Getter
public class OrcamentoItemPecaInsumo {

    private UUID id;
    private UUID pecasInsumosId;
    private UUID orcamentoId;
    private String descricao;
    private BigDecimal valorUnitario;
    private Integer quantidade;

    @Builder
    public OrcamentoItemPecaInsumo(UUID id, UUID pecasInsumosId, UUID orcamentoId, String descricao, BigDecimal valorUnitario, Integer quantidade) {
        this.id = requireNonNull(id);
        this.pecasInsumosId = requireNonNull(pecasInsumosId);
        this.orcamentoId = requireNonNull(orcamentoId);
        this.descricao = requireNonNull(descricao);
        this.valorUnitario = requireNonNull(valorUnitario);
        this.quantidade = requireNonNull(quantidade);
    }
}