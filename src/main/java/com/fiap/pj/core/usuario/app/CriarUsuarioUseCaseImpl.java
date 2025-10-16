package com.fiap.pj.core.usuario.app;


import com.fiap.pj.core.email.app.usecase.EnviarEmailUseCase;
import com.fiap.pj.core.email.app.usecase.command.EnviarEmailCommand;
import com.fiap.pj.core.email.domain.enums.Template;
import com.fiap.pj.core.usuario.app.gateways.UsuarioGateway;
import com.fiap.pj.core.usuario.app.usecase.CriarUsuarioUseCase;
import com.fiap.pj.core.usuario.app.usecase.command.CriarUsuarioCommand;
import com.fiap.pj.core.usuario.domain.Usuario;

import java.util.UUID;


public class CriarUsuarioUseCaseImpl implements CriarUsuarioUseCase {

    private final UsuarioGateway usuarioGateway;
    private final EnviarEmailUseCase enviarEmailUseCase;

    public CriarUsuarioUseCaseImpl(UsuarioGateway usuarioGateway, EnviarEmailUseCase enviarEmailUseCase) {
        this.usuarioGateway = usuarioGateway;
        this.enviarEmailUseCase = enviarEmailUseCase;
    }

    @Override
    public UUID handle(CriarUsuarioCommand cmd) {
        Usuario usuario = new Usuario(UUID.randomUUID(), cmd.getNome(), cmd.getSobreNome(), cmd.getEmail(), cmd.getSenha(), cmd.isAtivo());

        usuario.adicionarPerfils(cmd.getPerfis());
        usuarioGateway.salvar(usuario);

        this.enviarEmailUseCase.handle(
                new EnviarEmailCommand(
                        usuario.getEmail(),
                        Template.BOAS_VINDAS,
                        null
                )
        );

        return usuario.getId();
    }
}
