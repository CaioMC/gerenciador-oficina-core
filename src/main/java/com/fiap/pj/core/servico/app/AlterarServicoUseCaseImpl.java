package com.fiap.pj.core.servico.app;


import com.fiap.pj.core.servico.app.gateways.ServicoGateway;
import com.fiap.pj.core.servico.app.usecase.AlterarServicoUseCase;
import com.fiap.pj.core.servico.app.usecase.command.AlterarServicoCommand;
import com.fiap.pj.core.servico.exception.ServicoExceptions.ServicoNaoEncontradoException;


public class AlterarServicoUseCaseImpl implements AlterarServicoUseCase {

    private final ServicoGateway servicoGateway;

    public AlterarServicoUseCaseImpl(ServicoGateway servicoGateway) {
        this.servicoGateway = servicoGateway;
    }


    @Override
    public void handle(AlterarServicoCommand cmd) {
        var servico = servicoGateway.buscarPorId(cmd.getId()).orElseThrow(ServicoNaoEncontradoException::new);
        servico.alterar(cmd.getDescricao(), cmd.getValorUnitario(), cmd.getObservacao());
        servicoGateway.alterar(servico);
    }
}
