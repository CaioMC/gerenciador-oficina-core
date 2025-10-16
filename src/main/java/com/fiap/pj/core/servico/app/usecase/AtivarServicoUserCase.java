package com.fiap.pj.core.servico.app.usecase;

import com.fiap.pj.core.servico.app.usecase.command.AtivarServicoCommand;

public interface AtivarServicoUserCase {
    void handle(AtivarServicoCommand cmd);
}
