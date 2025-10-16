package com.fiap.pj.core.servico.app;


import com.fiap.pj.core.servico.app.gateways.ServicoGateway;
import com.fiap.pj.core.servico.app.usecase.ListarServicoUseCase;
import com.fiap.pj.infra.servico.controller.request.ListarServicoRequest;
import com.fiap.pj.infra.servico.controller.response.ServicoResponse;
import com.fiap.pj.infra.sk.api.Slice;


public class ListarServicoUseCaseImpl implements ListarServicoUseCase {

    private final ServicoGateway servicoGateway;

    public ListarServicoUseCaseImpl(ServicoGateway servicoGateway) {
        this.servicoGateway = servicoGateway;
    }

    @Override
    public Slice<ServicoResponse> handle(ListarServicoRequest request) {
        return servicoGateway.listarServico(request);
    }
}
