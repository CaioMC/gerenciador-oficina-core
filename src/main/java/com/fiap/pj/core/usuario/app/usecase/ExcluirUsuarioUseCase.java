package com.fiap.pj.core.usuario.app.usecase;

import com.fiap.pj.core.usuario.app.usecase.command.ExcluirUsuarioCommand;

public interface ExcluirUsuarioUseCase {

    void handle(ExcluirUsuarioCommand cmd);
}
