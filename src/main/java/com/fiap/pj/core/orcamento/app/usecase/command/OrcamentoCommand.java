package com.fiap.pj.core.orcamento.app.usecase.command;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;

import java.util.Set;
import java.util.UUID;

@Getter
public abstract class OrcamentoCommand {

    protected String descricao;

    @NotNull(message = "Identificador do cliente não pode ser nulo.")
    protected UUID clienteId;

    @NotNull(message = "Identificador do veiculo não pode ser nulo.")
    protected UUID veiculoId;

    protected UUID ordemServicoId;

    @PositiveOrZero(message = "Hodômetro do veiculo não pode ser negativa.")
    protected int hodometro;

    @Valid
    protected Set<OrcamentoItemServicoCommand> servicos;

    @Valid
    protected Set<OrcamentoItemPecaInsumoCommand> pecasInsumos;

    protected OrcamentoCommand(
            String descricao,
            UUID clienteId,
            UUID veiculoId,
            UUID ordemServicoId,
            int hodometro,
            Set<OrcamentoItemServicoCommand> servicos,
            Set<OrcamentoItemPecaInsumoCommand> pecasInsumos
    ) {
        this.descricao = descricao;
        this.clienteId = clienteId;
        this.veiculoId = veiculoId;
        this.ordemServicoId = ordemServicoId;
        this.hodometro = hodometro;
        this.servicos = servicos;
        this.pecasInsumos = pecasInsumos;
    }

    protected OrcamentoCommand(
            String descricao,
            UUID clienteId,
            UUID veiculoId,
            int hodometro,
            Set<OrcamentoItemServicoCommand> servicos,
            Set<OrcamentoItemPecaInsumoCommand> pecasInsumos
    ) {
        this.descricao = descricao;
        this.clienteId = clienteId;
        this.veiculoId = veiculoId;
        this.hodometro = hodometro;
        this.servicos = servicos;
        this.pecasInsumos = pecasInsumos;
    }
}