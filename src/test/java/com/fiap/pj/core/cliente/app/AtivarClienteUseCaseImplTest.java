package com.fiap.pj.core.cliente.app;


import com.fiap.pj.core.cliente.app.gateways.ClienteGateway;
import com.fiap.pj.core.cliente.app.usecase.command.AtivarClienteCommand;
import com.fiap.pj.core.cliente.domain.Cliente;
import com.fiap.pj.core.cliente.exception.ClienteExceptions.ClienteNaoEncontradoException;
import com.fiap.pj.core.cliente.util.factory.ClienteTestFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AtivarClienteUseCaseImplTest {

    @Mock
    private ClienteGateway clienteGateway;

    @InjectMocks
    private AtivarClienteUseCaseImpl ativarClienteUseCaseImpl;

    @Test
    void deveAtivarCliente() {
        var id = UUID.randomUUID();
        when(clienteGateway.buscarPorId(id)).thenReturn(Optional.of(ClienteTestFactory.umCliente()));
        ativarClienteUseCaseImpl.handle(new AtivarClienteCommand(id));
        verify(clienteGateway).alterar(Mockito.any(Cliente.class));
    }

    @Test
    void deveRetonarClienteNaoEncontradoException() {
        var id = UUID.randomUUID();
        Mockito.doThrow(new ClienteNaoEncontradoException())
                .when(clienteGateway)
                .buscarPorId(id);

        var thrown = catchThrowable(() -> ativarClienteUseCaseImpl.handle(new AtivarClienteCommand(id)));
        assertThat(thrown).isInstanceOf(ClienteNaoEncontradoException.class);

    }

}
