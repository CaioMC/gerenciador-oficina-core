package com.fiap.pj.core.orcamento.app;


import com.fiap.pj.core.orcamento.app.gateways.OrcamentoGateway;
import com.fiap.pj.core.orcamento.app.usecase.ListarOrcamentoUseCase;
import com.fiap.pj.infra.orcamento.controller.request.ListarOrcamentoRequest;
import com.fiap.pj.infra.orcamento.controller.response.OrcamentoResponse;
import com.fiap.pj.infra.sk.api.Slice;

public class ListarOrcamentoUseCaseImpl implements ListarOrcamentoUseCase {

    private final OrcamentoGateway orcamentoGateway;

    public ListarOrcamentoUseCaseImpl(OrcamentoGateway orcamentoGateway) {
        this.orcamentoGateway = orcamentoGateway;
    }

    @Override
    public Slice<OrcamentoResponse> handle(ListarOrcamentoRequest request) {
        return orcamentoGateway.listar(request);
    }
}
