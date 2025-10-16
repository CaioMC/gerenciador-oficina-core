package com.fiap.pj.core.pecainsumo.app.usecase.command;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class AlterarPecaInsumoCommand extends PecaInsumoCommand {

    private UUID id;

    public AlterarPecaInsumoCommand(String descricao, String modeloVeiculo, BigDecimal valorUnitario, Integer quantidadeEstoque, Integer quantidadeMinimoEstoque, UUID id) {
        super(descricao, modeloVeiculo, valorUnitario, quantidadeEstoque, quantidadeMinimoEstoque);
        this.id = id;
    }
}