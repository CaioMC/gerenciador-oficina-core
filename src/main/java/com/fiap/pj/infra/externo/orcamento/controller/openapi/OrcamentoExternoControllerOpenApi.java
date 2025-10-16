package com.fiap.pj.infra.externo.orcamento.controller.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

public interface OrcamentoExternoControllerOpenApi {

    @Operation(description = "Aprovar Orcamento Externo", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Orcamento externo aprovado com sucesso."),
            @ApiResponse(responseCode = "400", description = "O Orcamento externo não pode ser aprovado.")})
    ResponseEntity<Void> aprovarOrcamento(@PathVariable UUID id);

    @Operation(description = "Reprovar Orcamento Externo", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Orcamento externo reprovado com sucesso."),
            @ApiResponse(responseCode = "400", description = "O Orcamento externo não pode ser reprovado.")})
    ResponseEntity<Void> reprovarOrcamento(@PathVariable UUID id);
}