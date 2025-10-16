package com.fiap.pj.core.servico.app.usecase.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public abstract class ServicoCommand {

    @NotBlank(message = "Descricao do servico não pode estar vazio.")
    protected String descricao;
    protected BigDecimal valorUnitario;
    protected String observacao;

    protected ServicoCommand(String descricao, BigDecimal valorUnitario, String observacao) {
        this.descricao = descricao;
        this.valorUnitario = valorUnitario;
        this.observacao = observacao;
    }
}
