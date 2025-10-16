package com.fiap.pj.core.cliente.app;

import com.fiap.pj.core.cliente.app.gateways.ClienteGateway;
import com.fiap.pj.core.cliente.app.usecase.InativarClienteUserCase;
import com.fiap.pj.core.cliente.app.usecase.command.InativarClienteCommand;
import com.fiap.pj.core.cliente.exception.ClienteExceptions.ClienteNaoEncontradoException;


public class InativarClienteUseCaseImpl implements InativarClienteUserCase {

    private final ClienteGateway clienteGateway;

    public InativarClienteUseCaseImpl(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    @Override
    public void handle(InativarClienteCommand cmd) {
        var cliente = clienteGateway.buscarPorId(cmd.id()).orElseThrow(ClienteNaoEncontradoException::new);
        cliente.inativar();
        clienteGateway.alterar(cliente);
    }
}
