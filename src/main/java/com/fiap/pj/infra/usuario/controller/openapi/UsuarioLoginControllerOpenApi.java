package com.fiap.pj.infra.usuario.controller.openapi;

import com.fiap.pj.core.usuario.app.usecase.command.LoginUsuarioCommand;
import com.fiap.pj.infra.usuario.controller.response.LoginUsuarioResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface UsuarioLoginControllerOpenApi {

    @Operation(description = "Realiza o login do usuário", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Login realizado com sucesso."),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas.")})
    ResponseEntity<LoginUsuarioResponse> loginUsuario(@RequestBody LoginUsuarioCommand cmd);

}
