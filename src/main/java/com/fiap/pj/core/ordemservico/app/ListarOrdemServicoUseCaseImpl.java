package com.fiap.pj.core.ordemservico.app;

import com.fiap.pj.core.ordemservico.app.gateways.OrdemServicoGateway;
import com.fiap.pj.core.ordemservico.app.usecase.ListarOrdemServicoUseCase;
import com.fiap.pj.infra.ordemservico.controller.request.ListarOrdemServicoRequest;
import com.fiap.pj.infra.ordemservico.controller.response.OrdemServicoResponse;
import com.fiap.pj.infra.sk.api.Slice;


public class ListarOrdemServicoUseCaseImpl implements ListarOrdemServicoUseCase {

    private final OrdemServicoGateway ordemServicoGateway;

    public ListarOrdemServicoUseCaseImpl(OrdemServicoGateway ordemServicoGateway) {
        this.ordemServicoGateway = ordemServicoGateway;
    }

    @Override
    public Slice<OrdemServicoResponse> handle(ListarOrdemServicoRequest request) {
        return this.ordemServicoGateway.listar(request);
    }
}
