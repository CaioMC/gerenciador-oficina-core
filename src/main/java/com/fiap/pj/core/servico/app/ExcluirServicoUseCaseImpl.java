package com.fiap.pj.core.servico.app;

import com.fiap.pj.core.servico.app.gateways.ServicoGateway;
import com.fiap.pj.core.servico.app.usecase.ExcluirServicoUserCase;
import com.fiap.pj.core.servico.app.usecase.command.ExcluirServicoCommand;
import com.fiap.pj.core.servico.exception.ServicoExceptions.ServicoComRelacionamentoException;
import com.fiap.pj.core.servico.exception.ServicoExceptions.ServicoNaoEncontradoException;
import org.springframework.dao.DataIntegrityViolationException;


public class ExcluirServicoUseCaseImpl implements ExcluirServicoUserCase {

    private final ServicoGateway servicoGateway;

    public ExcluirServicoUseCaseImpl(ServicoGateway servicoGateway) {
        this.servicoGateway = servicoGateway;
    }

    @Override
    public void handle(ExcluirServicoCommand cmd) {
        var servico = servicoGateway.buscarPorId(cmd.id()).orElseThrow(ServicoNaoEncontradoException::new);
        try {
            servicoGateway.excluir(servico);
        } catch (DataIntegrityViolationException e) {
            throw new ServicoComRelacionamentoException();
        }
    }
}
