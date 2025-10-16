package com.fiap.pj.core.cliente.app.usecase;

import com.fiap.pj.core.cliente.app.usecase.command.ExcluirClienteCommand;

public interface ExcluirClienteUserCase {
    void handle(ExcluirClienteCommand cmd);
}
