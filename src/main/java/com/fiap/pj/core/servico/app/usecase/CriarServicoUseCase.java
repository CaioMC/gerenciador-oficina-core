package com.fiap.pj.core.servico.app.usecase;

import com.fiap.pj.core.servico.domain.Servico;
import com.fiap.pj.core.servico.app.usecase.command.CriarServicoCommand;

public interface CriarServicoUseCase {

    Servico handle(CriarServicoCommand cmd);
}
