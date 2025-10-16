package com.fiap.pj.core.pecainsumo.app;

import com.fiap.pj.core.pecainsumo.app.gateways.PecaInsumoGateway;
import com.fiap.pj.core.pecainsumo.app.usecase.AlterarPecaInsumoUseCase;
import com.fiap.pj.core.pecainsumo.app.usecase.command.AlterarPecaInsumoCommand;
import com.fiap.pj.core.pecainsumo.exception.PecaInsumoExceptions.PecaInsumoNaoEncontradoException;

public class AlterarPecaInsumoUseCaseImpl implements AlterarPecaInsumoUseCase {

    private final PecaInsumoGateway pecaInsumoGateway;

    public AlterarPecaInsumoUseCaseImpl(PecaInsumoGateway pecaInsumoGateway) {
        this.pecaInsumoGateway = pecaInsumoGateway;
    }

    @Override
    public void handle(AlterarPecaInsumoCommand cmd) {
        var pecaInsumo = pecaInsumoGateway.buscarPorId(cmd.getId()).orElseThrow(PecaInsumoNaoEncontradoException::new);
        pecaInsumo.atualizarDados(cmd.getDescricao(), cmd.getModeloVeiculo(), cmd.getValorUnitario(), cmd.getQuantidadeMinimoEstoque());
        pecaInsumoGateway.alterar(pecaInsumo);
    }
} 