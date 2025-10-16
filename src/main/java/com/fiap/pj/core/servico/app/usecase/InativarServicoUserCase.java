package com.fiap.pj.core.servico.app.usecase;

import com.fiap.pj.core.servico.app.usecase.command.InativarServicoCommand;

public interface InativarServicoUserCase {
    void handle(InativarServicoCommand cmd);
}
