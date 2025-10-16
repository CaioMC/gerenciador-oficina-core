package com.fiap.pj.core.cliente.app.usecase;

import com.fiap.pj.core.cliente.app.usecase.command.AtivarClienteCommand;

public interface AtivarClienteUserCase {
    void handle(AtivarClienteCommand cmd);
}
