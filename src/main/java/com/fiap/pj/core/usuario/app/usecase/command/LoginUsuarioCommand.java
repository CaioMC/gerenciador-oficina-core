package com.fiap.pj.core.usuario.app.usecase.command;

import jakarta.validation.constraints.NotBlank;

public record LoginUsuarioCommand(
        @NotBlank(message = "E-mail do usuário não pode estar vazio.")
        String email,
        @NotBlank(message = "Senha do usuário não pode estar vazio.")
        String senha) {
}
