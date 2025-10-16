package com.fiap.pj.core.externo.orcamento.app;

import com.fiap.pj.core.externo.orcamento.exception.OrcamentoExternoExceptions.OrcamentoTempoLimiteExcedidoException;
import com.fiap.pj.core.orcamento.app.gateways.OrcamentoGateway;
import com.fiap.pj.core.externo.orcamento.usecase.ReprovarOrcamentoExternoUseCase;
import com.fiap.pj.core.orcamento.app.usecase.ReprovarOrcamentoUseCase;
import com.fiap.pj.core.orcamento.app.usecase.command.ReprovarOrcamentoCommand;
import com.fiap.pj.core.orcamento.domain.Orcamento;
import com.fiap.pj.core.orcamento.exception.OrcamentoExceptions.OrcamentoNaoEncontradoException;
import com.fiap.pj.core.util.DateTimeUtils;

import java.util.UUID;

public class ReprovarOrcamentoExternoUseCaseImpl implements ReprovarOrcamentoExternoUseCase {

    private final OrcamentoGateway orcamentoGateway;
    private final ReprovarOrcamentoUseCase reprovarOrcamentoUseCase;

    public ReprovarOrcamentoExternoUseCaseImpl(OrcamentoGateway orcamentoGateway, ReprovarOrcamentoUseCase reprovarOrcamentoUseCase) {
        this.orcamentoGateway = orcamentoGateway;
        this.reprovarOrcamentoUseCase = reprovarOrcamentoUseCase;
    }

    public void handle(UUID id) {
        Orcamento orcamento = orcamentoGateway.buscarPorId(id).orElseThrow(OrcamentoNaoEncontradoException::new);

        boolean isValid = DateTimeUtils.validarTempoMenor30Min(orcamento.getDataCriacao());
        if (!isValid)
            throw new OrcamentoTempoLimiteExcedidoException();

        this.reprovarOrcamentoUseCase.handle(new ReprovarOrcamentoCommand(id));
    }
}
