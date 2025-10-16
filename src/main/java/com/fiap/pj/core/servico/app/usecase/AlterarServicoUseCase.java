package com.fiap.pj.core.servico.app.usecase;

import com.fiap.pj.core.servico.app.usecase.command.AlterarServicoCommand;

public interface AlterarServicoUseCase {

    void handle(AlterarServicoCommand cmd);
}
