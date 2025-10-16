package com.fiap.pj.core.cliente.app.usecase;

import com.fiap.pj.core.cliente.domain.Cliente;
import com.fiap.pj.core.cliente.app.usecase.command.AlterarClienteCommand;

public interface AlterarClienteUserCase {

    Cliente handle(AlterarClienteCommand cmd);

}
