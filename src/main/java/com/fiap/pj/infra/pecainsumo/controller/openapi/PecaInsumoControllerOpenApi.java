package com.fiap.pj.infra.pecainsumo.controller.openapi;

import com.fiap.pj.core.pecainsumo.app.usecase.command.AlterarPecaInsumoCommand;
import com.fiap.pj.core.pecainsumo.app.usecase.command.CriarPecaInsumoCommand;
import com.fiap.pj.infra.pecainsumo.controller.request.ListarPecaInsumoRequest;
import com.fiap.pj.infra.pecainsumo.controller.response.PecaInsumoResponse;
import com.fiap.pj.infra.sk.api.Slice;
import com.fiap.pj.infra.sk.web.ResponseEntityUtils.ResponseId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Tag(name = "pecas-insumos-controller")
public interface PecaInsumoControllerOpenApi {

    @Operation(summary = "Criar peça/insumo", description = "Cria uma nova peça ou insumo")
    ResponseEntity<ResponseId> criarPecaInsumo(@RequestBody CriarPecaInsumoCommand cmd);

    @Operation(summary = "Alterar peça/insumo", description = "Altera uma peça ou insumo existente")
    ResponseEntity<Void> alterarPecaInsumo(@PathVariable UUID id, @RequestBody AlterarPecaInsumoCommand cmd);

    @Operation(summary = "Listar peças/insumos", description = "Lista todas as peças e insumos com paginação e filtros. Use 'estoqueBaixo=true' para filtrar apenas itens com estoque baixo.")
    Slice<PecaInsumoResponse> listarPecaInsumo(
            @Parameter(description = "Filtros para busca (nome, descricao, estoqueBaixo)") ListarPecaInsumoRequest filterRequest,
            @Parameter(description = "Configurações de paginação") Pageable pageable
    );

    @Operation(summary = "Excluir peça/insumo", description = "Exclui uma peça ou insumo pelo ID")
    ResponseEntity<Void> excluirPecaInsumo(@PathVariable UUID id);
} 