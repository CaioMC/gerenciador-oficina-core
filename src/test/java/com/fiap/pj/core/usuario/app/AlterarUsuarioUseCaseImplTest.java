package com.fiap.pj.core.usuario.app;


import com.fiap.pj.core.usuario.app.gateways.UsuarioGateway;
import com.fiap.pj.core.usuario.domain.Usuario;
import com.fiap.pj.core.usuario.exception.UsuarioExceptions.UsuarioNaoEncontradoException;
import com.fiap.pj.core.usuario.util.factrory.UsuarioTestFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.fiap.pj.core.usuario.util.factrory.UsuarioTestFactory.ALTER_LAST_NAME;
import static com.fiap.pj.core.usuario.util.factrory.UsuarioTestFactory.ALTER_NAME;
import static com.fiap.pj.core.usuario.util.factrory.UsuarioTestFactory.ALTER_PASSWORD;
import static com.fiap.pj.core.usuario.util.factrory.UsuarioTestFactory.ALTER_USER_ROLE;
import static com.fiap.pj.core.usuario.util.factrory.UsuarioTestFactory.ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AlterarUsuarioUseCaseImplTest {


    @Captor
    ArgumentCaptor<Usuario> userCaptor;

    @Mock
    private UsuarioGateway usuarioGateway;

    @InjectMocks
    private AlterarUsuarioUseCaseImpl alterarUsuarioUseCaseImpl;

    @Test
    void deveAlterarUsuario() {
        var user = UsuarioTestFactory.umUsuario();
        when(usuarioGateway.buscarPorId(user.getId())).thenReturn(Optional.of(user));

        alterarUsuarioUseCaseImpl.handle(UsuarioTestFactory.UmAlterarUsuarioCommand(user.getId()));

        verify(usuarioGateway).alterar(userCaptor.capture());
        Usuario usuario = userCaptor.getValue();

        assertNotNull(usuario);
        assertEquals(ID, usuario.getId());
        assertEquals(ALTER_NAME, usuario.getNome());
        assertEquals(ALTER_LAST_NAME, usuario.getSobreNome());
        assertEquals(ALTER_PASSWORD, usuario.getSenha());
        assertTrue(usuario.getPerfis().contains(ALTER_USER_ROLE));
    }

    @Test
    void deveRetornarUsuarioNaoEncontratoException() {
        var user = UsuarioTestFactory.umUsuario();

        Mockito.doThrow(new UsuarioNaoEncontradoException())
                .when(usuarioGateway)
                .buscarPorId(user.getId());

        var thrown = catchThrowable(() -> alterarUsuarioUseCaseImpl.handle(UsuarioTestFactory.UmAlterarUsuarioCommand(user.getId())));
        assertThat(thrown).isInstanceOf(UsuarioNaoEncontradoException.class);

    }
}
