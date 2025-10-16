package com.fiap.pj.core.usuario.app.usecase;

import com.fiap.pj.infra.sk.api.Slice;
import com.fiap.pj.infra.usuario.controller.request.ListarUsuarioRequest;
import com.fiap.pj.infra.usuario.controller.response.UsuarioReponse;

public interface ListarUsuarioUseCase {

    Slice<UsuarioReponse> handle(ListarUsuarioRequest request);
}
