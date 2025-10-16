package com.fiap.pj.core.usuario.app;


import com.fiap.pj.core.usuario.app.gateways.UsuarioGateway;
import com.fiap.pj.core.usuario.app.usecase.ExcluirUsuarioUseCase;
import com.fiap.pj.core.usuario.app.usecase.command.ExcluirUsuarioCommand;
import com.fiap.pj.core.usuario.exception.UsuarioExceptions.UsuarioComRelacionamentoException;
import com.fiap.pj.core.usuario.exception.UsuarioExceptions.UsuarioNaoEncontradoException;
import org.springframework.dao.DataIntegrityViolationException;


public class ExcluirUsuarioUseCaseImpl implements ExcluirUsuarioUseCase {

    private final UsuarioGateway usuarioGateway;

    public ExcluirUsuarioUseCaseImpl(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public void handle(ExcluirUsuarioCommand cmd) {
        var usuario = usuarioGateway.buscarPorId(cmd.id()).orElseThrow(UsuarioNaoEncontradoException::new);
        try {
            usuarioGateway.excluir(usuario);
        } catch (DataIntegrityViolationException e) {
            throw new UsuarioComRelacionamentoException();
        }
    }
}
