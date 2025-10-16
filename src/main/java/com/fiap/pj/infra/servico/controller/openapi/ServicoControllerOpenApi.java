package com.fiap.pj.infra.servico.controller.openapi;

import com.fiap.pj.infra.servico.controller.request.ListarServicoRequest;
import com.fiap.pj.infra.servico.controller.response.ServicoResponse;
import com.fiap.pj.core.servico.app.usecase.command.AlterarServicoCommand;
import com.fiap.pj.core.servico.app.usecase.command.CriarServicoCommand;
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

public interface ServicoControllerOpenApi {

    @Operation(description = "Cria um novo servico", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Servico criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "O  Servico não pode ser criado.")})
    ResponseEntity<ResponseId> criarServico(@Valid @RequestBody CriarServicoCommand cmd);

    @Operation(description = "Desativar um Servico", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Servico desativado com sucesso."),
            @ApiResponse(responseCode = "400", description = "O  Servico não pode ser desativado.")})
    ResponseEntity<Void> inativarServico(@PathVariable UUID id);

    @Operation(description = "Ativar um Servico", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Servico Ativado com sucesso."),
            @ApiResponse(responseCode = "400", description = "O  Servico não pode ser ativado.")})
    ResponseEntity<Void> ativarServico(@PathVariable UUID id);


    @Operation(description = "Alterar um  Servico", method = "PUT")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Servico alterado com sucesso."),
            @ApiResponse(responseCode = "400", description = "O  Servico não pode ser alterado.")})
    ResponseEntity<Void> alterarServico(@Valid @PathVariable UUID id, @RequestBody AlterarServicoCommand cmd);


    @Operation(description = "Retorna uma lista de servico.", method = "GET")
    Slice<ServicoResponse> listarServico(@ParameterObject ListarServicoRequest filterRequest, @ParameterObject Pageable pageable);


    @Operation(description = "Excluir um servico", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Servico excluido com sucesso."),
            @ApiResponse(responseCode = "400", description = "O  Servico não pode ser excluido.")})
    ResponseEntity<Void> excluirServico(@PathVariable UUID id);

}
