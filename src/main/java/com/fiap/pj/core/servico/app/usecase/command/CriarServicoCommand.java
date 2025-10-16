package com.fiap.pj.core.servico.app.usecase.command;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CriarServicoCommand extends ServicoCommand {

    public CriarServicoCommand(String descricao, BigDecimal valorUnitario, String observacao) {
        super(descricao, valorUnitario, observacao);
    }
}
