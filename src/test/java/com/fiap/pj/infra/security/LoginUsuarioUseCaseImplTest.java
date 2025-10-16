package com.fiap.pj.infra.security;


import com.fiap.pj.infra.helpers.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static com.fiap.pj.core.usuario.util.factrory.UsuarioTestFactory.umLoginUsuarioCommand;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginUsuarioUseCaseImplTest {


    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private LoginUsuarioUseCaseImpl loginUsuarioUseCaseImpl;

    @Test
    void deveEfetuarLoginUsuarior() {
        UserDetailsImpl userDetails = mock(UserDetailsImpl.class);
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);


        when(jwtUtil.generateToken(Mockito.any(UserDetailsImpl.class))).thenReturn("token");
        var response = loginUsuarioUseCaseImpl.handle(umLoginUsuarioCommand());

        assertEquals("token", response.token());

    }


}
