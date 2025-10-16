package com.fiap.pj.core.cliente.app;

import com.fiap.pj.core.cliente.app.gateways.ClienteGateway;
import com.fiap.pj.core.cliente.app.usecase.ExcluirClienteUserCase;
import com.fiap.pj.core.cliente.app.usecase.command.ExcluirClienteCommand;
import com.fiap.pj.core.cliente.exception.ClienteExceptions.ClienteComRelacionamentoException;
import com.fiap.pj.core.cliente.exception.ClienteExceptions.ClienteNaoEncontradoException;
import org.springframework.dao.DataIntegrityViolationException;


public class ExcluirClienteUseCaseImpl implements ExcluirClienteUserCase {

    private final ClienteGateway clienteGateway;

    public ExcluirClienteUseCaseImpl(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    @Override
    public void handle(ExcluirClienteCommand cmd) {
        var cliente = clienteGateway.buscarPorId(cmd.id()).orElseThrow(ClienteNaoEncontradoException::new);
        try {
            clienteGateway.excluir(cliente);
        } catch (DataIntegrityViolationException e) {
            throw new ClienteComRelacionamentoException();
        }
    }
}
