package com.fiap.pj.core.cliente.app;


import com.fiap.pj.core.cliente.app.gateways.ClienteGateway;
import com.fiap.pj.core.cliente.domain.Cliente;
import com.fiap.pj.core.cliente.exception.ClienteExceptions.DocumentoIdentificacaoDuplicadoException;
import com.fiap.pj.core.cliente.util.factory.ClienteTestFactory;
import com.fiap.pj.core.email.app.usecase.EnviarEmailUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.fiap.pj.core.cliente.util.factory.ClienteTestFactory.ENDERECO;
import static com.fiap.pj.core.cliente.util.factory.ClienteTestFactory.NUMERO_DOCUMENTO;
import static com.fiap.pj.core.cliente.util.factory.ClienteTestFactory.TELEFONE;
import static com.fiap.pj.core.usuario.util.factrory.UsuarioTestFactory.E_MAIL;
import static com.fiap.pj.core.usuario.util.factrory.UsuarioTestFactory.NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CriarClienteUseCaseImplTest {

    @Mock
    private ClienteGateway clienteGateway;

    @Mock
    private EnviarEmailUseCase enviarEmailUseCase;

    @InjectMocks
    private CriarClienteUseCaseImpl criarClienteUseCaseImpl;

    @Test
    void deveCriarCliente() {
        when(clienteGateway.salvar(any(Cliente.class))).thenReturn(ClienteTestFactory.umCliente());


        var cliente = criarClienteUseCaseImpl.handle(ClienteTestFactory.umCriarClienteCommand());

        assertEquals(NAME, cliente.getNome());
        assertEquals(E_MAIL, cliente.getEmail());
        assertEquals(TELEFONE, cliente.getTelefone());
        assertEquals(ENDERECO, cliente.getEndereco());
        assertEquals(NUMERO_DOCUMENTO, cliente.getDocumentoIdentificacao().getNumero());
        assertTrue(cliente.isAtivo());
    }

    @Test
    void deveRetornarDocumentoIdentificacaoDuplicadoException() {

        Mockito.when(clienteGateway.existsByDocumentoIdentificacaoNumero(Mockito.anyString())).thenReturn(true);

        var thrown = catchThrowable(() -> criarClienteUseCaseImpl.handle(ClienteTestFactory.umCriarClienteCommand()));
        assertThat(thrown).isInstanceOf(DocumentoIdentificacaoDuplicadoException.class);

    }
}
