package com.fiap.pj.core.pecainsumo.app;

import com.fiap.pj.core.pecainsumo.app.gateways.PecaInsumoGateway;
import com.fiap.pj.core.pecainsumo.app.usecase.ExcluirPecaInsumoUseCase;
import com.fiap.pj.core.pecainsumo.app.usecase.command.ExcluirPecaInsumoCommand;
import com.fiap.pj.core.pecainsumo.exception.PecaInsumoExceptions.PecaInsumoComRelacionamentoException;
import com.fiap.pj.core.pecainsumo.exception.PecaInsumoExceptions.PecaInsumoNaoEncontradoException;
import org.springframework.dao.DataIntegrityViolationException;


public class ExcluirPecaInsumoUseCaseImpl implements ExcluirPecaInsumoUseCase {

    private final PecaInsumoGateway pecaInsumoGateway;

    public ExcluirPecaInsumoUseCaseImpl(PecaInsumoGateway pecaInsumoGateway) {
        this.pecaInsumoGateway = pecaInsumoGateway;
    }

    @Override
    public void handle(ExcluirPecaInsumoCommand cmd) {
        var pecaInsumo = pecaInsumoGateway.buscarPorId(cmd.getId()).orElseThrow(PecaInsumoNaoEncontradoException::new);
        try {
            pecaInsumoGateway.excluir(pecaInsumo);
        } catch (DataIntegrityViolationException e) {
            throw new PecaInsumoComRelacionamentoException();
        }

    }
} 