package com.fiap.pj.infra.cliente.controller.openapi;

import com.fiap.pj.core.cliente.app.usecase.command.AlterarClienteCommand;
import com.fiap.pj.core.cliente.app.usecase.command.CriarClienteCommand;
import com.fiap.pj.infra.cliente.controller.request.ListarClienteRequest;
import com.fiap.pj.infra.cliente.controller.response.ClienteResponse;
import com.fiap.pj.infra.sk.api.Slice;
import com.fiap.pj.infra.sk.web.ResponseEntityUtils.ResponseId;
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

public interface ClienteControllerOpenApi {

    @Operation(description = "Cria um novo Cliente", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Cliente criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "O  Cliente não pode ser criado.")})
    ResponseEntity<ResponseId> criarCliente(@Valid @RequestBody CriarClienteCommand cmd);

    @Operation(description = "Alterar um  Cliente", method = "PUT")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Cliente alterado com sucesso."),
            @ApiResponse(responseCode = "400", description = "O  Cliente não pode ser alterado.")})
    ResponseEntity<Void> alterarCliente(@Valid @PathVariable UUID id, @RequestBody AlterarClienteCommand cmd);


    @Operation(description = "Desativar um Cliente", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Cliente desativado com sucesso."),
            @ApiResponse(responseCode = "400", description = "O  Cliente não pode ser desativado.")})
    ResponseEntity<Void> inativarCliente(@PathVariable UUID id);

    @Operation(description = "Ativar um Cliente", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Cliente Ativado com sucesso."),
            @ApiResponse(responseCode = "400", description = "O  Cliente não pode ser ativado.")})
    ResponseEntity<Void> ativarCliente(@PathVariable UUID id);

    @Operation(description = "Retorna uma lista de Clientes.", method = "GET")
    Slice<ClienteResponse> listarCliente(@ParameterObject ListarClienteRequest filterRequest, @ParameterObject Pageable pageable);


    @Operation(description = "Excluir um Cliente", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Cliente excluido com sucesso."),
            @ApiResponse(responseCode = "400", description = "O  Cliente não pode ser excluido.")})
    ResponseEntity<Void> excluirCliente(@PathVariable UUID id);
}