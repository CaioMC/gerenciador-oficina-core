package com.fiap.pj.infra.pecainsumo.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

@Entity
@Table(name = "pecas_insumos")
@NoArgsConstructor
@Getter
public class PecaInsumoEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private BigDecimal valorUnitario;

    @Column(nullable = false)
    private Integer quantidadeEstoque;

    @Column(nullable = false)
    private Integer quantidadeMinimoEstoque;

    private String modeloVeiculo;

    
    public PecaInsumoEntity(UUID id, String descricao, String modeloVeiculo, BigDecimal valorUnitario, Integer quantidadeEstoque, Integer quantidadeMinimoEstoque) {
        this.id = requireNonNull(id);
        this.modeloVeiculo = requireNonNull(modeloVeiculo);
        this.descricao = requireNonNull(descricao);
        this.valorUnitario = requireNonNull(valorUnitario);
        this.quantidadeEstoque = requireNonNull(quantidadeEstoque);
        this.quantidadeMinimoEstoque = requireNonNull(quantidadeMinimoEstoque);
    }

} 