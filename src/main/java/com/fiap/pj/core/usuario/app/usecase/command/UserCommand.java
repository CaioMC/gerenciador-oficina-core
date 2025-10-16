package com.fiap.pj.core.usuario.app.usecase.command;


import com.fiap.pj.core.usuario.domain.enums.Perfil;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.Set;

import static java.util.Objects.requireNonNull;

@Getter
public abstract class UserCommand {

    @NotBlank(message = "Nome do usuário não pode estar vazio.")
    protected String nome;

    protected String sobreNome;

    @NotBlank(message = "Senha do usuário não pode estar vazio.")
    protected String senha;

    protected boolean ativo;

    protected Set<Perfil> perfis;

    protected UserCommand(String nome, String sobreNome, String senha, boolean ativo, Set<Perfil> perfis) {
        this.nome = requireNonNull(nome);
        this.sobreNome = sobreNome;
        this.senha = requireNonNull(senha);
        this.ativo = ativo;
        this.perfis = requireNonNull(perfis);
    }
}




