package com.fiap.pj.infra.orcamento.controller.openapi;

import com.fiap.pj.core.orcamento.app.usecase.command.AlterarOrcamentoCommand;
import com.fiap.pj.core.orcamento.app.usecase.command.CriarOrcamentoCommand;
import com.fiap.pj.infra.orcamento.controller.request.ListarOrcamentoRequest;
import com.fiap.pj.infra.orcamento.controller.response.OrcamentoResponse;
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

public interface OrcamentoControllerOpenApi {

    @Operation(description = "Cria um novo Orcamento", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Orcamento criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "O Orcamento não pode ser criado.")})
    ResponseEntity<ResponseId> criarOrcamento(@Valid @RequestBody CriarOrcamentoCommand cmd);

    @Operation(description = "Reprovar Orcamento", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Orcamento reprovado com sucesso."),
            @ApiResponse(responseCode = "400", description = "O Orcamento não pode ser reprovado.")})
    ResponseEntity<Void> reprovarOrcamento(@PathVariable UUID id);

    @Operation(description = "Aprovar Orcamento", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Orcamento aprovado com sucesso."),
            @ApiResponse(responseCode = "400", description = "O Orcamento não pode ser aprovado.")})
    ResponseEntity<Void> aprovarOrcamento(@PathVariable UUID id);

    @Operation(description = "Alterar Orcamento", method = "PUT")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Orcamento alterado com sucesso."),
            @ApiResponse(responseCode = "400", description = "O Orcamento não pode ser alterado.")})
    ResponseEntity<Void> alterarOrcamento(@PathVariable UUID id, @Valid @RequestBody AlterarOrcamentoCommand cmd);

    @Operation(description = "Retorna uma lista de orçamento.", method = "GET")
    Slice<OrcamentoResponse> listarOrcamento(@ParameterObject ListarOrcamentoRequest filterRequest, @ParameterObject Pageable pageable);


}
