package com.fiap.pj.core.orcamento.app.usecase.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
public class AlterarOrcamentoCommand extends OrcamentoCommand {

    @Setter
    @JsonIgnore
    private UUID id;

    public AlterarOrcamentoCommand(UUID id, String descricao, UUID clienteId, UUID veiculoId, int hodometro, Set<OrcamentoItemServicoCommand> servicos, Set<OrcamentoItemPecaInsumoCommand> pecasInsumos) {
        super(descricao, clienteId, veiculoId, hodometro, servicos, pecasInsumos);
        this.id = id;
    }
}
