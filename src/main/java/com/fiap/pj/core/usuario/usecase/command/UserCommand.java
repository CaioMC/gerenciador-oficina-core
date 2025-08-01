package com.fiap.pj.core.usuario.usecase.command;


import com.fiap.pj.core.usuario.domain.enums.Perfil;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.Set;

import static java.util.Objects.requireNonNull;

@Getter
public abstract class UserCommand {

    @NotBlank(message = "Nome do usuário não pode estar vazio.")
    protected String name;

    protected String lastName;

    @NotBlank(message = "E-mail do usuário não pode estar vazio.")
    protected String email;

    @NotBlank(message = "Senha do usuário não pode estar vazio.")
    protected String password;

    protected boolean active;

    protected Set<Perfil> roles;

    protected UserCommand(String name, String lastName, String email, String password, boolean active, Set<Perfil> roles) {
        this.name = requireNonNull(name);
        this.lastName = lastName;
        this.email = requireNonNull(email);
        this.password = requireNonNull(password);
        this.active = active;
        this.roles = requireNonNull(roles);
    }
}




