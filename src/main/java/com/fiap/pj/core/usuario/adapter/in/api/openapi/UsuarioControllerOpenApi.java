package com.fiap.pj.core.usuario.adapter.in.api.openapi;

import com.fiap.pj.core.usuario.adapter.in.api.request.ListarUsuarioRequest;
import com.fiap.pj.core.usuario.adapter.in.api.response.UsuarioReponse;
import com.fiap.pj.core.usuario.usecase.command.AlterarUsuarioCommand;
import com.fiap.pj.core.usuario.usecase.command.CriarUsuarioCommand;
import com.fiap.pj.infra.api.Slice;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public interface UsuarioControllerOpenApi {

    @Operation(description = "Cria um novo usuário", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Usuario criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "O  Usuário não pode ser criado.")})
    ResponseEntity<Void> criarUsuario(@Valid @RequestBody CriarUsuarioCommand cmd);


    @Operation(description = "Alterar um  usuário", method = "PUT")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Usuario alterado com sucesso."),
            @ApiResponse(responseCode = "400", description = "O  Usuário não pode ser alterado.")})
    ResponseEntity<Void> alteraUsuario(@Valid @PathVariable UUID id, @RequestBody AlterarUsuarioCommand cmd);

    @Operation(description = "Desativar um usuário", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Usuario desativado com sucesso."),
            @ApiResponse(responseCode = "400", description = "O  Usuário não pode ser desativado.")})
    ResponseEntity<Void> inativarUsuario(@PathVariable UUID id);

    @Operation(description = "Ativar um usuário", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Usuario Ativado com sucesso."),
            @ApiResponse(responseCode = "400", description = "O  Usuário não pode ser ativado.")})
    ResponseEntity<Void> ativarUsuario(@PathVariable UUID id);

    @Operation(description = "Retorna uma lista de usuarios.", method = "GET")
    Slice<UsuarioReponse> listarUsuario(@ParameterObject ListarUsuarioRequest filterRequest, Pageable pageable);
}
