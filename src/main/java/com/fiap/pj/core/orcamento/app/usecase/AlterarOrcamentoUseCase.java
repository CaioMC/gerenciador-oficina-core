package com.fiap.pj.core.orcamento.app.usecase;

import com.fiap.pj.core.orcamento.app.usecase.command.AlterarOrcamentoCommand;

public interface AlterarOrcamentoUseCase {

    void handle(AlterarOrcamentoCommand cmd);
}
