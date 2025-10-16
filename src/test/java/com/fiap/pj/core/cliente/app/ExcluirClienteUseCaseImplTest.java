package com.fiap.pj.core.cliente.app;


import com.fiap.pj.core.cliente.app.gateways.ClienteGateway;
import com.fiap.pj.core.cliente.app.usecase.command.ExcluirClienteCommand;
import com.fiap.pj.core.cliente.domain.Cliente;
import com.fiap.pj.core.cliente.exception.ClienteExceptions.ClienteComRelacionamentoException;
import com.fiap.pj.core.cliente.util.factory.ClienteTestFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExcluirClienteUseCaseImplTest {

    @Mock
    private ClienteGateway clienteGateway;

    @InjectMocks
    private ExcluirClienteUseCaseImpl excluirClienteUseCaseImpl;


    @Test
    void deveExcluirCliente() {
        var id = UUID.randomUUID();
        when(clienteGateway.buscarPorId(id)).thenReturn(Optional.of(ClienteTestFactory.umCliente()));
        excluirClienteUseCaseImpl.handle(new ExcluirClienteCommand(id));
        verify(clienteGateway).excluir(Mockito.any(Cliente.class));
    }


    @Test
    void deveRetornarServicoComRelacionamentoException() {
        var id = UUID.randomUUID();

        when(clienteGateway.buscarPorId(id)).thenReturn(Optional.of(ClienteTestFactory.umCliente()));
        Mockito.doThrow(DataIntegrityViolationException.class)
                .when(clienteGateway).excluir(Mockito.any(Cliente.class));
        Throwable thrown = catchThrowable(() -> excluirClienteUseCaseImpl.handle(new ExcluirClienteCommand(id)));
        assertThat(thrown).isInstanceOf(ClienteComRelacionamentoException.class);
    }
}
