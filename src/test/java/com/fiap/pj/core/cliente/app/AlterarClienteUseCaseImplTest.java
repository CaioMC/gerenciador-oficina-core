package com.fiap.pj.core.cliente.app;


import com.fiap.pj.core.cliente.app.gateways.ClienteGateway;
import com.fiap.pj.core.cliente.domain.Cliente;
import com.fiap.pj.core.cliente.exception.ClienteExceptions.ClienteNaoEncontradoException;
import com.fiap.pj.core.cliente.util.factory.ClienteTestFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AlterarClienteUseCaseImplTest {

    @Captor
    ArgumentCaptor<Cliente> clienteCaptor;
    @Mock
    private ClienteGateway clienteGateway;
    @InjectMocks
    private AlterarClienteUseCaseImpl alterarClienteUseCaseImpl;

    @Test
    void deveAlteraCliente() {
        var cliente = ClienteTestFactory.umCliente();
        when(clienteGateway.buscarPorId(cliente.getId())).thenReturn(Optional.of(cliente));

        alterarClienteUseCaseImpl.handle(ClienteTestFactory.umAlterarClienteCommand(cliente.getId()));

        verify(clienteGateway).salvar(clienteCaptor.capture());
        Cliente clienteUpdated = clienteCaptor.getValue();

        assertNotNull(clienteUpdated);
        assertEquals(ClienteTestFactory.ID, clienteUpdated.getId());
        assertEquals(ClienteTestFactory.NOME_ALTERADO, clienteUpdated.getNome());
        assertEquals(ClienteTestFactory.TELEFONE_ALTERADO, clienteUpdated.getTelefone());
        assertEquals(ClienteTestFactory.E_MAIL_ALTERADO, clienteUpdated.getEmail());
        assertEquals(ClienteTestFactory.ENDERECO_ALTERADO, clienteUpdated.getEndereco());

    }

    @Test
    void deveRetornarClienteNaoEncontratoException() {
        var cliente = ClienteTestFactory.umCliente();

        Mockito.doThrow(new ClienteNaoEncontradoException())
                .when(clienteGateway)
                .buscarPorId(cliente.getId());

        var thrown = catchThrowable(() -> alterarClienteUseCaseImpl.handle(ClienteTestFactory.umAlterarClienteCommand(cliente.getId())));
        assertThat(thrown).isInstanceOf(ClienteNaoEncontradoException.class);
    }
}
