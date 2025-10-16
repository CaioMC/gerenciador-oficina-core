package com.fiap.pj.infra.cliente.controller.openapi;

import com.fiap.pj.core.veiculo.app.usecase.command.AdicionarVeiculoClienteCommand;
import com.fiap.pj.core.veiculo.app.usecase.command.RemoverVeiculoClienteCommand;
import com.fiap.pj.infra.sk.web.ResponseEntityUtils.ResponseId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public interface ClienteVeiculoControllerOpenApi {


    @Operation(description = "Adicionar veiculo ao Cliente", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Veículo adicionado com sucesso."),
            @ApiResponse(responseCode = "400", description = "O  Veículo não pode ser adicionado ao cliente.")})
    ResponseEntity<ResponseId> adicionarVeiculo(@PathVariable UUID id, @RequestBody AdicionarVeiculoClienteCommand cmd);

    @Operation(description = "Remover veiculo do Cliente", method = "DELETE")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Veículo removido com sucesso."),
            @ApiResponse(responseCode = "400", description = "O Veículo não pode ser removido do cliente."),
            @ApiResponse(responseCode = "404", description = "Cliente ou veículo não encontrado.")})
    ResponseEntity<Void> removerVeiculo(@PathVariable UUID id, @RequestBody RemoverVeiculoClienteCommand cmd);

}
