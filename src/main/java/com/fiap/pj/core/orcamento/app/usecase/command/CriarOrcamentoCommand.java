package com.fiap.pj.core.orcamento.app.usecase.command;

import lombok.Getter;

import java.util.Set;
import java.util.UUID;

@Getter
public class CriarOrcamentoCommand extends OrcamentoCommand {

    public CriarOrcamentoCommand(
            String descricao,
            UUID clienteId,
            UUID veiculoId,
            UUID ordemServicoId,
            int hodometro,
            Set<OrcamentoItemServicoCommand> servicos,
            Set<OrcamentoItemPecaInsumoCommand> pecasInsumos) {
        super(descricao, clienteId, veiculoId, ordemServicoId, hodometro, servicos, pecasInsumos);
    }
}
