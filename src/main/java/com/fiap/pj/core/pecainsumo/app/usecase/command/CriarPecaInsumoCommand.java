package com.fiap.pj.core.pecainsumo.app.usecase.command;

public class CriarPecaInsumoCommand extends PecaInsumoCommand {

    public CriarPecaInsumoCommand(String descricao, String modeloVeiculo, java.math.BigDecimal valorUnitario, Integer quantidadeEstoque, Integer quantidadeMinimoEstoque) {
        super(modeloVeiculo, descricao, valorUnitario, quantidadeEstoque, quantidadeMinimoEstoque);
    }
} 