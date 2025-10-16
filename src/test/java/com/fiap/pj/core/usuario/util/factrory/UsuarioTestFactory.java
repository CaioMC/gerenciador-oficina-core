package com.fiap.pj.core.usuario.util.factrory;


import com.fiap.pj.core.usuario.app.usecase.command.AlterarUsuarioCommand;
import com.fiap.pj.core.usuario.app.usecase.command.CriarUsuarioCommand;
import com.fiap.pj.core.usuario.app.usecase.command.LoginUsuarioCommand;
import com.fiap.pj.core.usuario.domain.Usuario;
import com.fiap.pj.core.usuario.domain.enums.Perfil;
import com.fiap.pj.infra.usuario.persistence.UsuarioEntity;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class UsuarioTestFactory {

    public static final UUID ID = UUID.randomUUID();
    public static final String NAME = "Hakuna";
    public static final String LAST_NAME = "Matata";
    public static final String E_MAIL = "hakuna.matata@gmail.com";
    public static final String PASSWORD = "1234";
    public static final Perfil USER_ROLE = Perfil.ADM;

    public static final String ALTER_NAME = "Jack";
    public static final String ALTER_LAST_NAME = "Daniels";
    public static final String ALTER_E_MAIL = "jack.daniels@gmail.com";
    public static final String ALTER_PASSWORD = "4321";
    public static final Perfil ALTER_USER_ROLE = Perfil.ATENDENTE;

    public static Usuario umUsuario() {
        var usuario = new Usuario(ID, NAME, LAST_NAME, E_MAIL, PASSWORD, true);
        usuario.adicionarPerfils(List.of(USER_ROLE));
        return usuario;
    }

    public static UsuarioEntity umUsuarioEntity() {
        return new UsuarioEntity(ID, NAME, LAST_NAME, E_MAIL, PASSWORD, true, Set.of(USER_ROLE));
    }

    public static CriarUsuarioCommand umCriarUsuarioCommand() {
        return new CriarUsuarioCommand(NAME, LAST_NAME, E_MAIL, PASSWORD, true, Set.of(USER_ROLE));
    }

    public static AlterarUsuarioCommand UmAlterarUsuarioCommand(UUID id) {
        return new AlterarUsuarioCommand(id, ALTER_NAME, ALTER_LAST_NAME, ALTER_PASSWORD, true, Set.of(ALTER_USER_ROLE));
    }

    public static LoginUsuarioCommand umLoginUsuarioCommand() {
        return new LoginUsuarioCommand(E_MAIL, "2025");
    }
}
