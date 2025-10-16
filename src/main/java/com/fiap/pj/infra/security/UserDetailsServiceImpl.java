package com.fiap.pj.infra.security;

import com.fiap.pj.core.usuario.exception.UsuarioExceptions.UsuarioNaoEncontradoException;
import com.fiap.pj.infra.usuario.persistence.UsuarioRepositoryJpa;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepositoryJpa repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsuarioNaoEncontradoException {
        var usuario = repository.findByEmail(email)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("O Usuário não foi encontrado com o e-mail: " + email));

        var perfis = usuario.getPerfis().stream()
                .map(perfil -> new SimpleGrantedAuthority(perfil.name())).toList();

        return new UserDetailsImpl(usuario, perfis);
    }
}