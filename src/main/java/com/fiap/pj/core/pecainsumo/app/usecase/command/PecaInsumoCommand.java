package com.fiap.pj.core.pecainsumo.app.usecase.command;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public abstract class PecaInsumoCommand {

    protected final String descricao;
    protected final String modeloVeiculo;
    protected final BigDecimal valorUnitario;
    protected final Integer quantidadeEstoque;
    protected final Integer quantidadeMinimoEstoque;

    protected PecaInsumoCommand(String descricao, String modeloVeiculo, BigDecimal valorUnitario, Integer quantidadeEstoque, Integer quantidadeMinimoEstoque) {
        this.modeloVeiculo = modeloVeiculo;
        this.descricao = descricao;
        this.valorUnitario = valorUnitario;
        this.quantidadeEstoque = quantidadeEstoque;
        this.quantidadeMinimoEstoque = quantidadeMinimoEstoque;
    }
} 