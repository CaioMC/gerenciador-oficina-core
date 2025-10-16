package com.fiap.pj.core.orcamento.app.usecase;

import com.fiap.pj.core.orcamento.app.usecase.command.CriarOrcamentoCommand;
import com.fiap.pj.core.orcamento.domain.Orcamento;

public interface CriarOrcamentoUseCase {

    Orcamento handle(CriarOrcamentoCommand cmd);
}
