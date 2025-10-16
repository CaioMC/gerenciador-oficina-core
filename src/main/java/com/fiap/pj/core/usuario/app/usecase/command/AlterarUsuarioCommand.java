package com.fiap.pj.core.usuario.app.usecase.command;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap.pj.core.usuario.domain.enums.Perfil;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;


@Getter
public class AlterarUsuarioCommand extends UserCommand {

    @Setter
    @JsonIgnore
    private UUID id;

    public AlterarUsuarioCommand(UUID id, String nome, String sobreNome, String senha, boolean ativo, Set<Perfil> perfis) {
        super(nome, sobreNome, senha, ativo, perfis);
        this.id = id;
    }
}




