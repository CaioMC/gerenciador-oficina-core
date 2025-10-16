package com.fiap.pj.core.servico.app;

import com.fiap.pj.core.servico.app.gateways.ServicoGateway;
import com.fiap.pj.core.servico.app.usecase.AtivarServicoUserCase;
import com.fiap.pj.core.servico.app.usecase.command.AtivarServicoCommand;
import com.fiap.pj.core.servico.exception.ServicoExceptions.ServicoNaoEncontradoException;


public class AtivarServicoUseCaseImpl implements AtivarServicoUserCase {

    private final ServicoGateway servicoGateway;

    public AtivarServicoUseCaseImpl(ServicoGateway servicoGateway) {
        this.servicoGateway = servicoGateway;
    }

    @Override
    public void handle(AtivarServicoCommand cmd) {
        var servico = servicoGateway.buscarPorId(cmd.id()).orElseThrow(ServicoNaoEncontradoException::new);
        servico.ativar();
        servicoGateway.alterar(servico);
    }
}
