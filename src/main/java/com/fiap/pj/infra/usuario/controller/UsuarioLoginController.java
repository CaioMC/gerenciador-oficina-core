package com.fiap.pj.infra.usuario.controller;

import com.fiap.pj.core.usuario.app.usecase.LoginUsuarioUseCase;
import com.fiap.pj.core.usuario.app.usecase.command.LoginUsuarioCommand;
import com.fiap.pj.infra.usuario.controller.openapi.UsuarioLoginControllerOpenApi;
import com.fiap.pj.infra.usuario.controller.response.LoginUsuarioResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = UsuarioLoginController.PATH)
@AllArgsConstructor
public class UsuarioLoginController implements UsuarioLoginControllerOpenApi {

    public static final String PATH = "v1/usuarios/login";

    private final LoginUsuarioUseCase loginUsuarioUseCase;

    @Override
    @PostMapping
    public ResponseEntity<LoginUsuarioResponse> loginUsuario(LoginUsuarioCommand cmd) {
        var login = loginUsuarioUseCase.handle(cmd);
        return ResponseEntity.ok(login);
    }


}
