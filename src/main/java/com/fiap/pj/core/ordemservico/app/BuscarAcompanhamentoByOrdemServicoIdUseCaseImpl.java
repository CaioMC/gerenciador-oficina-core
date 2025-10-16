package com.fiap.pj.core.ordemservico.app;

import com.fiap.pj.core.ordemservico.app.gateways.OrdemServicoGateway;
import com.fiap.pj.core.ordemservico.app.usecase.BuscarAcompanhamentoByOrdemServicoIdUseCase;
import com.fiap.pj.infra.ordemservico.controller.request.BuscarAcompanhamentoByOrdemServicoIdRequest;
import com.fiap.pj.infra.ordemservico.controller.response.AcompanhamentoOrdemServicoResponse;

import java.util.Optional;


public class BuscarAcompanhamentoByOrdemServicoIdUseCaseImpl implements BuscarAcompanhamentoByOrdemServicoIdUseCase {

    private final OrdemServicoGateway ordemServicoGateway;

    public BuscarAcompanhamentoByOrdemServicoIdUseCaseImpl(OrdemServicoGateway ordemServicoGateway) {
        this.ordemServicoGateway = ordemServicoGateway;
    }

    @Override
    public Optional<AcompanhamentoOrdemServicoResponse> handle(BuscarAcompanhamentoByOrdemServicoIdRequest request) {
        return ordemServicoGateway.buscarAcompanhamentoByOrdemServicoId(request.getId());
    }
}
