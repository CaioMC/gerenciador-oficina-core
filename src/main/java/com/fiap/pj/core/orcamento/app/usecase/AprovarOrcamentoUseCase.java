package com.fiap.pj.core.orcamento.app.usecase;

import com.fiap.pj.core.orcamento.app.usecase.command.AprovarOrcamentoCommand;

public interface AprovarOrcamentoUseCase {

    void handle(AprovarOrcamentoCommand cmd);
}
