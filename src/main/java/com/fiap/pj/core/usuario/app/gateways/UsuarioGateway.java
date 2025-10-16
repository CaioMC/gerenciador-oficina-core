package com.fiap.pj.core.usuario.app.gateways;

import com.fiap.pj.core.usuario.domain.Usuario;
import com.fiap.pj.infra.sk.api.Slice;
import com.fiap.pj.infra.sk.jpa.BaseRepositoryGateway;
import com.fiap.pj.infra.usuario.controller.request.ListarUsuarioRequest;
import com.fiap.pj.infra.usuario.controller.response.UsuarioReponse;

public interface UsuarioGateway extends BaseRepositoryGateway<Usuario> {

    Slice<UsuarioReponse> listarUsuarios(ListarUsuarioRequest request);
}
