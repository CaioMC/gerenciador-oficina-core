package com.fiap.pj.core.cliente.app.usecase.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import static java.util.Objects.requireNonNull;

@Getter
public abstract class ClienteCommand {

    @NotBlank(message = "Nome do cliente não pode estar vazio.")
    protected String nome;
    protected String email;
    protected String telefone;

    @NotBlank(message = "Endereco do cliente não pode estar vazio.")
    protected String endereco;

    protected ClienteCommand(String nome, String email, String telefone, String endereco) {
        this.nome = requireNonNull(nome);
        this.email = email;
        this.telefone = telefone;
        this.endereco = requireNonNull(endereco);
    }
}
