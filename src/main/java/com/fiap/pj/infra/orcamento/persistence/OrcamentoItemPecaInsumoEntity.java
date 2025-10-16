package com.fiap.pj.infra.orcamento.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

import static java.util.Objects.requireNonNull;


@Entity
@Table(name = "orcamentos_pecas_insumos")
@NoArgsConstructor
@Getter
public class OrcamentoItemPecaInsumoEntity {

    @Id
    private UUID id;
    private UUID pecasInsumosId;
    private UUID orcamentoId;
    private String descricao;
    private BigDecimal valorUnitario;
    private Integer quantidade;

    @Builder
    public OrcamentoItemPecaInsumoEntity(UUID id, UUID pecasInsumosId, UUID orcamentoId, String descricao, BigDecimal valorUnitario, Integer quantidade) {
        this.id = requireNonNull(id);
        this.pecasInsumosId = requireNonNull(pecasInsumosId);
        this.orcamentoId = requireNonNull(orcamentoId);
        this.descricao = requireNonNull(descricao);
        this.valorUnitario = requireNonNull(valorUnitario);
        this.quantidade = requireNonNull(quantidade);
    }

    public BigDecimal valorTotal() {
        return this.getValorUnitario().multiply(BigDecimal.valueOf(quantidade));
    }
}