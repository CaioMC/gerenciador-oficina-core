package com.fiap.pj.core.cliente.app;

import com.fiap.pj.core.cliente.app.gateways.ClienteGateway;
import com.fiap.pj.core.cliente.app.usecase.AtivarClienteUserCase;
import com.fiap.pj.core.cliente.app.usecase.command.AtivarClienteCommand;
import com.fiap.pj.core.cliente.exception.ClienteExceptions.ClienteNaoEncontradoException;


public class AtivarClienteUseCaseImpl implements AtivarClienteUserCase {

    private final ClienteGateway clienteGateway;

    public AtivarClienteUseCaseImpl(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    @Override
    public void handle(AtivarClienteCommand cmd) {
        var cliente = clienteGateway.buscarPorId(cmd.id()).orElseThrow(ClienteNaoEncontradoException::new);
        cliente.ativar();
        clienteGateway.alterar(cliente);
    }
}
