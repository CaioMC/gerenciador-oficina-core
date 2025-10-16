package com.fiap.pj.core.pecainsumo.app;

import com.fiap.pj.core.pecainsumo.app.gateways.PecaInsumoGateway;
import com.fiap.pj.core.pecainsumo.app.usecase.CriarPecaInsumoUseCase;
import com.fiap.pj.core.pecainsumo.app.usecase.command.CriarPecaInsumoCommand;
import com.fiap.pj.core.pecainsumo.domain.PecaInsumo;

import java.util.UUID;


public class CriarPecaInsumoUseCaseImpl implements CriarPecaInsumoUseCase {

    private final PecaInsumoGateway pecaInsumoGateway;

    public CriarPecaInsumoUseCaseImpl(PecaInsumoGateway pecaInsumoGateway) {
        this.pecaInsumoGateway = pecaInsumoGateway;
    }

    @Override
    public PecaInsumo handle(CriarPecaInsumoCommand cmd) {
        var pecaInsumo = new PecaInsumo(
                UUID.randomUUID(),
                cmd.getModeloVeiculo(),
                cmd.getDescricao(),
                cmd.getValorUnitario(),
                cmd.getQuantidadeEstoque(),
                cmd.getQuantidadeMinimoEstoque()
        );
        return pecaInsumoGateway.salvar(pecaInsumo);
    }
} 