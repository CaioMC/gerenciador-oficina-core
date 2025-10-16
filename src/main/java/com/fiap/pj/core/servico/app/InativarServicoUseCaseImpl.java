package com.fiap.pj.core.servico.app;

import com.fiap.pj.core.servico.app.gateways.ServicoGateway;
import com.fiap.pj.core.servico.app.usecase.InativarServicoUserCase;
import com.fiap.pj.core.servico.app.usecase.command.InativarServicoCommand;
import com.fiap.pj.core.servico.exception.ServicoExceptions.ServicoNaoEncontradoException;


public class InativarServicoUseCaseImpl implements InativarServicoUserCase {

    private final ServicoGateway servicoGateway;

    public InativarServicoUseCaseImpl(ServicoGateway servicoGateway) {
        this.servicoGateway = servicoGateway;
    }

    @Override
    public void handle(InativarServicoCommand cmd) {
        var servico = servicoGateway.buscarPorId(cmd.id()).orElseThrow(ServicoNaoEncontradoException::new);
        servico.inativar();
        servicoGateway.alterar(servico);
    }
}
