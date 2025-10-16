package com.fiap.pj.core.cliente.app.usecase.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class AlterarClienteCommand extends ClienteCommand {

    @Setter
    @JsonIgnore
    private UUID id;

    public AlterarClienteCommand(UUID id, String nome, String email, String telefone, String endereco) {
        super(nome, email, telefone, endereco);
        this.id = id;
    }
}
