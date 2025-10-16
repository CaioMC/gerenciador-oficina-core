package com.fiap.pj.core.servico.app.usecase.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter

public class AlterarServicoCommand extends ServicoCommand {

    @JsonIgnore
    @Setter
    private UUID id;

    public AlterarServicoCommand(String descricao, BigDecimal valorUnitario, String observacao, UUID id) {
        super(descricao, valorUnitario, observacao);
        this.id = id;
    }

}
