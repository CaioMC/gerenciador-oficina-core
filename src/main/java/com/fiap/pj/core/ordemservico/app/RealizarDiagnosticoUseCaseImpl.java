package com.fiap.pj.core.ordemservico.app;

import com.fiap.pj.core.ordemservico.app.gateways.OrdemServicoGateway;
import com.fiap.pj.core.ordemservico.app.usecase.RealizarDiagnosticoUseCase;
import com.fiap.pj.core.ordemservico.app.usecase.command.RealizarDiagnosticoOrdemServicoCommand;
import com.fiap.pj.core.ordemservico.domain.OrdemServico;
import com.fiap.pj.core.ordemservico.exception.OrdemServicoExceptions.OrdemServicoNaoEncontradaException;


public class RealizarDiagnosticoUseCaseImpl implements RealizarDiagnosticoUseCase {

    private final OrdemServicoGateway ordemServicoGateway;

    public RealizarDiagnosticoUseCaseImpl(OrdemServicoGateway ordemServicoGateway) {
        this.ordemServicoGateway = ordemServicoGateway;
    }

    @Override
    public void handle(RealizarDiagnosticoOrdemServicoCommand cmd) {
        OrdemServico ordemServico = this.ordemServicoGateway.buscarPorId(cmd.getOrdemServicoId()).orElseThrow(OrdemServicoNaoEncontradaException::new);
        ordemServico.realizarDiagnostico(cmd.getDescricao());
        this.ordemServicoGateway.salvar(ordemServico);
    }
}