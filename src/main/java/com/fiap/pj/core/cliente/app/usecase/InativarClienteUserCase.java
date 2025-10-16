package com.fiap.pj.core.cliente.app.usecase;

import com.fiap.pj.core.cliente.app.usecase.command.InativarClienteCommand;

public interface InativarClienteUserCase {

    void handle(InativarClienteCommand cmd);

}
