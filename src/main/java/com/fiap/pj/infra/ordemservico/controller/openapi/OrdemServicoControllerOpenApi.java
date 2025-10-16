package com.fiap.pj.infra.ordemservico.controller.openapi;

import com.fiap.pj.core.ordemservico.app.usecase.command.RealizarDiagnosticoOrdemServicoCommand;
import com.fiap.pj.infra.ordemservico.controller.request.ListarOrdemServicoRequest;
import com.fiap.pj.infra.ordemservico.controller.response.AcompanhamentoOrdemServicoResponse;
import com.fiap.pj.infra.ordemservico.controller.response.OrdemServicoResponse;
import com.fiap.pj.infra.sk.api.Slice;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;
import java.util.UUID;

public interface OrdemServicoControllerOpenApi {

    @Operation(description = "Retorna uma lista de Ordem de Serviço.", method = "GET")
    Slice<OrdemServicoResponse> listarOrdemServico(@ParameterObject ListarOrdemServicoRequest filterRequest, @ParameterObject Pageable pageable);

    @Operation(description = "Retorna uma lista de Acompanhamento da Ordem de Serviço.", method = "GET")
    Optional<AcompanhamentoOrdemServicoResponse> buscarAcompanhamentoByOrdemServicoId(@PathVariable UUID id);

    @Operation(description = "Mover Ordem de serviço para em diagnóstico", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Ordem de serviço movida para em diagnóstico."),
            @ApiResponse(responseCode = "400", description = "A order de serviço não pode ser movida para em diagnóstico.")})
    ResponseEntity<Void> moverEmDiagnostico(@PathVariable UUID id);

    @Operation(description = "Mover Ordem de serviço para aguardando aprovação", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Ordem de serviço movida para aguardando aprovação."),
            @ApiResponse(responseCode = "400", description = "A order de serviço não pode ser movida para aguardando aprovação.")})
    ResponseEntity<Void> moverAguardandoAprovacao(@PathVariable UUID id);

    @Operation(description = "Mover Ordem de serviço para em execução", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Ordem de serviço movida para em execução."),
            @ApiResponse(responseCode = "400", description = "A order de serviço não pode ser movida para em execução.")})
    ResponseEntity<Void> moverEmExecucao(@PathVariable UUID id);

    @Operation(description = "Mover Ordem de serviço para finalizada", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Ordem de serviço movida para finalizada."),
            @ApiResponse(responseCode = "400", description = "A order de serviço não pode ser movida para finalizada.")})
    ResponseEntity<Void> moverFinalizada(@PathVariable UUID id);

    @Operation(description = "Mover Ordem de serviço para aguardando retirada", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Ordem de serviço movida para aguardando retirada."),
            @ApiResponse(responseCode = "400", description = "A order de serviço não pode ser movida para aguardando retirada.")})
    ResponseEntity<Void> moverAguardandoRetirada(@PathVariable UUID id);

    @Operation(description = "Mover Ordem de serviço para entregue", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Ordem de serviço movida para entregue."),
            @ApiResponse(responseCode = "400", description = "A order de serviço não pode ser movida para entregue.")})
    ResponseEntity<Void> moverEntregue(@PathVariable UUID id);

    @Operation(description = "Realizar Diagnostico Ordem de serviço", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Diagnóstico para ordem de serviço realizada com sucesso."),
            @ApiResponse(responseCode = "400", description = "O Diagnóstico para ordem de serviço não pode ser incluído.")})
    ResponseEntity<Void> realizarDiagnostico(@PathVariable UUID id, @RequestBody RealizarDiagnosticoOrdemServicoCommand cmd);
}
