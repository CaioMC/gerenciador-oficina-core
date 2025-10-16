package com.fiap.pj.core.usuario.app;


import com.fiap.pj.core.usuario.app.gateways.UsuarioGateway;
import com.fiap.pj.core.usuario.app.usecase.ListarUsuarioUseCase;
import com.fiap.pj.infra.sk.api.Slice;
import com.fiap.pj.infra.usuario.controller.request.ListarUsuarioRequest;
import com.fiap.pj.infra.usuario.controller.response.UsuarioReponse;


public class ListarUsuariorUseCaseImpl implements ListarUsuarioUseCase {

    private final UsuarioGateway usuarioGateway;

    public ListarUsuariorUseCaseImpl(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public Slice<UsuarioReponse> handle(ListarUsuarioRequest request) {
        return usuarioGateway.listarUsuarios(request);
    }
}
