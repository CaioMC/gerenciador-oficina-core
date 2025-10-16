package com.fiap.pj.core.usuario.app.usecase.command;


import com.fiap.pj.core.usuario.domain.enums.Perfil;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.Set;

import static java.util.Objects.requireNonNull;


@Getter
public class CriarUsuarioCommand extends UserCommand {


    @NotBlank(message = "E-mail do usuário não pode estar vazio.")
    protected String email;

    public CriarUsuarioCommand(String nome, String sobreNome, String email, String senha, boolean active, Set<Perfil> perfis) {
        super(nome, sobreNome, senha, active, perfis);
        this.email = requireNonNull(email);
    }
}





