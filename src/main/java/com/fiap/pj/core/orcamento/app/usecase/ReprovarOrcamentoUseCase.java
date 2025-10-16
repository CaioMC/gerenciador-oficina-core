package com.fiap.pj.core.orcamento.app.usecase;

import com.fiap.pj.core.orcamento.app.usecase.command.ReprovarOrcamentoCommand;

public interface ReprovarOrcamentoUseCase {

    void handle(ReprovarOrcamentoCommand cmd);
}
