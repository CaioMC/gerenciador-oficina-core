package com.fiap.pj.core.servico.app;


import com.fiap.pj.core.servico.app.gateways.ServicoGateway;
import com.fiap.pj.core.servico.app.usecase.CriarServicoUseCase;
import com.fiap.pj.core.servico.app.usecase.command.CriarServicoCommand;
import com.fiap.pj.core.servico.domain.Servico;

import java.util.UUID;

public class CriarServicoUseCaseImpl implements CriarServicoUseCase {

    private final ServicoGateway servicoGateway;

    public CriarServicoUseCaseImpl(ServicoGateway servicoGateway) {
        this.servicoGateway = servicoGateway;
    }

    @Override
    public Servico handle(CriarServicoCommand cmd) {
        var service = new Servico(UUID.randomUUID(), cmd.getDescricao(), cmd.getValorUnitario(), cmd.getObservacao(), true);
        return servicoGateway.salvar(service);
    }
}
