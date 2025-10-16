package com.fiap.pj.core.usuario.app;


import com.fiap.pj.core.usuario.app.gateways.UsuarioGateway;
import com.fiap.pj.core.usuario.app.usecase.command.ExcluirUsuarioCommand;
import com.fiap.pj.core.usuario.domain.Usuario;
import com.fiap.pj.core.usuario.exception.UsuarioExceptions.UsuarioComRelacionamentoException;
import com.fiap.pj.core.usuario.util.factrory.UsuarioTestFactory;
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
class ExcluirUsuarioUseCaseImplTest {

    @Mock
    private UsuarioGateway usuarioGateway;

    @InjectMocks
    private ExcluirUsuarioUseCaseImpl excluirUsuarioUseCaseImpl;

    @Test
    void deveExcluirUsuario() {
        var id = UUID.randomUUID();
        when(usuarioGateway.buscarPorId(id)).thenReturn(Optional.of(UsuarioTestFactory.umUsuario()));
        excluirUsuarioUseCaseImpl.handle(new ExcluirUsuarioCommand(id));
        verify(usuarioGateway).excluir(Mockito.any(Usuario.class));
    }

    @Test
    void deveRetornarUsuarioComRelacionamentoException() {
        var id = UUID.randomUUID();

        when(usuarioGateway.buscarPorId(id)).thenReturn(Optional.of(UsuarioTestFactory.umUsuario()));
        Mockito.doThrow(DataIntegrityViolationException.class)
                .when(usuarioGateway).excluir(Mockito.any(Usuario.class));
        Throwable thrown = catchThrowable(() -> excluirUsuarioUseCaseImpl.handle(new ExcluirUsuarioCommand(id)));
        assertThat(thrown).isInstanceOf(UsuarioComRelacionamentoException.class);
    }
}
