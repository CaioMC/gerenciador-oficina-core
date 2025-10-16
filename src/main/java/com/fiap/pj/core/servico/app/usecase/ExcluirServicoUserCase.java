package com.fiap.pj.core.servico.app.usecase;

import com.fiap.pj.core.servico.app.usecase.command.ExcluirServicoCommand;

public interface ExcluirServicoUserCase {
    void handle(ExcluirServicoCommand cmd);
}
