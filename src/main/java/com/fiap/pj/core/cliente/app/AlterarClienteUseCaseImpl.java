package com.fiap.pj.core.cliente.app;

import com.fiap.pj.core.cliente.app.gateways.ClienteGateway;
import com.fiap.pj.core.cliente.app.usecase.AlterarClienteUserCase;
import com.fiap.pj.core.cliente.app.usecase.command.AlterarClienteCommand;
import com.fiap.pj.core.cliente.domain.Cliente;
import com.fiap.pj.core.cliente.exception.ClienteExceptions.ClienteNaoEncontradoException;

public class AlterarClienteUseCaseImpl implements AlterarClienteUserCase {

    private final ClienteGateway clienteGateway;

    public AlterarClienteUseCaseImpl(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    @Override
    public Cliente handle(AlterarClienteCommand cmd) {
        var cliente = clienteGateway.buscarPorId(cmd.getId()).orElseThrow(ClienteNaoEncontradoException::new);
        cliente.alterar(cmd.getNome(), cmd.getEmail(), cmd.getEndereco(), cmd.getTelefone());
        return clienteGateway.salvar(cliente);
    }
}
