package com.fiap.pj.core.usuario.app.usecase;

import com.fiap.pj.core.usuario.app.usecase.command.AlterarUsuarioCommand;

public interface AlterarUsuarioUseCase {

    void handle(AlterarUsuarioCommand cmd);
}
