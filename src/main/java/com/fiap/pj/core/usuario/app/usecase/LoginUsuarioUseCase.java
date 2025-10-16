package com.fiap.pj.core.usuario.app.usecase;

import com.fiap.pj.core.usuario.app.usecase.command.LoginUsuarioCommand;
import com.fiap.pj.infra.usuario.controller.response.LoginUsuarioResponse;

public interface LoginUsuarioUseCase {

    LoginUsuarioResponse handle(LoginUsuarioCommand cmd);
}
