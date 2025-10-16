package com.fiap.pj.core.externo.orcamento.app;

import com.fiap.pj.core.externo.orcamento.exception.OrcamentoExternoExceptions.OrcamentoTempoLimiteExcedidoException;
import com.fiap.pj.core.orcamento.app.gateways.OrcamentoGateway;
import com.fiap.pj.core.externo.orcamento.usecase.AprovarOrcamentoExternoUseCase;
import com.fiap.pj.core.orcamento.app.usecase.AprovarOrcamentoUseCase;
import com.fiap.pj.core.orcamento.app.usecase.command.AprovarOrcamentoCommand;
import com.fiap.pj.core.orcamento.domain.Orcamento;
import com.fiap.pj.core.orcamento.exception.OrcamentoExceptions.OrcamentoNaoEncontradoException;
import com.fiap.pj.core.util.DateTimeUtils;

import java.util.UUID;

public class AprovarOrcamentoExternoUseCaseImpl implements AprovarOrcamentoExternoUseCase {

    private final OrcamentoGateway orcamentoGateway;
    private final AprovarOrcamentoUseCase aprovarOrcamentoUseCase;

    public AprovarOrcamentoExternoUseCaseImpl(OrcamentoGateway orcamentoGateway, AprovarOrcamentoUseCase aprovarOrcamentoUseCase) {
        this.orcamentoGateway = orcamentoGateway;
        this.aprovarOrcamentoUseCase = aprovarOrcamentoUseCase;
    }

    public void handle(UUID id) {
        Orcamento orcamento = orcamentoGateway.buscarPorId(id).orElseThrow(OrcamentoNaoEncontradoException::new);

        boolean isValid = DateTimeUtils.validarTempoMenor30Min(orcamento.getDataCriacao());
        if (!isValid)
            throw new OrcamentoTempoLimiteExcedidoException();

        this.aprovarOrcamentoUseCase.handle(new AprovarOrcamentoCommand(id));
    }
}