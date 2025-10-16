package com.fiap.pj.core.usuario.app;

import com.fiap.pj.core.usuario.app.gateways.UsuarioGateway;
import com.fiap.pj.core.usuario.util.factrory.UsuarioTestFactory;
import com.fiap.pj.infra.sk.api.Slice;
import com.fiap.pj.infra.usuario.controller.request.ListarUsuarioRequest;
import com.fiap.pj.infra.usuario.controller.response.UsuarioReponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListarUsuarioServiceTest {

    @Mock
    private UsuarioGateway usuarioGateway;

    @InjectMocks
    private ListarUsuariorUseCaseImpl listarUsuariorUseCaseImpl;

    @Test
    void deveListarUsuarios() {
        var request = new ListarUsuarioRequest(UsuarioTestFactory.NAME, PageRequest.of(0, 10), true);
        UsuarioReponse usuarioResponseMock = Mockito.mock(UsuarioReponse.class);
        when(usuarioResponseMock.getId()).thenReturn(UsuarioTestFactory.ID.toString());
        when(usuarioResponseMock.getNome()).thenReturn(UsuarioTestFactory.NAME);
        when(usuarioResponseMock.getSobreNome()).thenReturn(UsuarioTestFactory.LAST_NAME);
        when(usuarioResponseMock.getEmail()).thenReturn(UsuarioTestFactory.E_MAIL);

        var slice = new Slice<>(false, List.of(usuarioResponseMock));

        when(usuarioGateway.listarUsuarios(request))
                .thenReturn(slice);

        var result = listarUsuariorUseCaseImpl.handle(request);

        assertNotNull(result);

        var item = result.getItems().stream().findFirst().orElse(null);
        assertNotNull(item);
        assertEquals(1, result.getItems().size());
        assertEquals(UsuarioTestFactory.ID.toString(), item.getId());
        assertEquals(UsuarioTestFactory.NAME, item.getNome());
        assertEquals(UsuarioTestFactory.LAST_NAME, item.getSobreNome());
        assertEquals(UsuarioTestFactory.E_MAIL, item.getEmail());

    }

} 