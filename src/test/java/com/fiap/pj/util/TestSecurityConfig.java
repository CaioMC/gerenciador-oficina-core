package com.fiap.pj.util;

import com.fiap.pj.core.usuario.util.factrory.UsuarioTestFactory;
import com.fiap.pj.infra.security.UserDetailsImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public class TestSecurityConfig {


    public static void setAuthentication() {
        UserDetailsImpl principal = new UserDetailsImpl(UsuarioTestFactory.umUsuarioEntity(), List.of());
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(principal, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
