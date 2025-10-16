package com.fiap.pj.core.pecainsumo.app;

import com.fiap.pj.core.pecainsumo.app.gateways.PecaInsumoGateway;
import com.fiap.pj.core.pecainsumo.app.usecase.ListarPecaInsumoUseCase;
import com.fiap.pj.infra.pecainsumo.controller.request.ListarPecaInsumoRequest;
import com.fiap.pj.infra.pecainsumo.controller.response.PecaInsumoResponse;
import com.fiap.pj.infra.sk.api.Slice;


public class ListarPecaInsumoUseCaseImpl implements ListarPecaInsumoUseCase {

    private final PecaInsumoGateway pecaInsumoGateway;

    public ListarPecaInsumoUseCaseImpl(PecaInsumoGateway pecaInsumoGateway) {
        this.pecaInsumoGateway = pecaInsumoGateway;
    }

    @Override
    public Slice<PecaInsumoResponse> handle(ListarPecaInsumoRequest filterRequest) {
        return pecaInsumoGateway.listar(filterRequest);
    }
} 