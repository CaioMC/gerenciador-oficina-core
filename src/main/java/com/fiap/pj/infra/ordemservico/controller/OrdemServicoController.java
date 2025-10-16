package com.fiap.pj.infra.ordemservico.controller;

import com.fiap.pj.core.ordemservico.app.usecase.AlterarStatusOsAguardandoAprovacaoUseCase;
import com.fiap.pj.core.ordemservico.app.usecase.BuscarAcompanhamentoByOrdemServicoIdUseCase;
import com.fiap.pj.core.ordemservico.app.usecase.ListarOrdemServicoUseCase;
import com.fiap.pj.core.ordemservico.app.usecase.MoverAguardandoRetiradaUseCase;
import com.fiap.pj.core.ordemservico.app.usecase.MoverEmDiagnosticoUseCase;
import com.fiap.pj.core.ordemservico.app.usecase.MoverEmExecucaoUseCase;
import com.fiap.pj.core.ordemservico.app.usecase.MoverEntregueUseCase;
import com.fiap.pj.core.ordemservico.app.usecase.MoverFinalizadaUseCase;
import com.fiap.pj.core.ordemservico.app.usecase.RealizarDiagnosticoUseCase;
import com.fiap.pj.core.ordemservico.app.usecase.command.RealizarDiagnosticoOrdemServicoCommand;
import com.fiap.pj.infra.ordemservico.controller.openapi.OrdemServicoControllerOpenApi;
import com.fiap.pj.infra.ordemservico.controller.request.BuscarAcompanhamentoByOrdemServicoIdRequest;
import com.fiap.pj.infra.ordemservico.controller.request.ListarOrdemServicoRequest;
import com.fiap.pj.infra.ordemservico.controller.response.AcompanhamentoOrdemServicoResponse;
import com.fiap.pj.infra.ordemservico.controller.response.OrdemServicoResponse;
import com.fiap.pj.infra.sk.api.Slice;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = OrdemServicoController.PATH)
@AllArgsConstructor
public class OrdemServicoController implements OrdemServicoControllerOpenApi {

    public static final String PATH = "v1/ordens-servicos";

    private final ListarOrdemServicoUseCase listarClienteUseCase;
    private final RealizarDiagnosticoUseCase realizarDiagnosticoUseCase;
    private final MoverEmDiagnosticoUseCase moverEmDiagnosticoUseCase;
    private final AlterarStatusOsAguardandoAprovacaoUseCase alterarStatusOsAguardandoAprovacaoUseCase;
    private final MoverFinalizadaUseCase moverFinalizadaUseCase;
    private final MoverAguardandoRetiradaUseCase moverAguardandoRetiradaUseCase;
    private final MoverEntregueUseCase moverEntregueUseCase;
    private final MoverEmExecucaoUseCase moverEmExecucaoUseCase;
    private final BuscarAcompanhamentoByOrdemServicoIdUseCase buscarAcompanhamentoByOrdemServicoIdUseCase;

    @Override
    @GetMapping
    public Slice<OrdemServicoResponse> listarOrdemServico(@ParameterObject ListarOrdemServicoRequest filterRequest, @ParameterObject Pageable pageable) {
        filterRequest.setPageable(pageable);
        return this.listarClienteUseCase.handle(filterRequest);
    }

    @Override
    @GetMapping("/{id}/acompanhamentos")
    public Optional<AcompanhamentoOrdemServicoResponse> buscarAcompanhamentoByOrdemServicoId(@PathVariable UUID id) {
        return this.buscarAcompanhamentoByOrdemServicoIdUseCase.handle(new BuscarAcompanhamentoByOrdemServicoIdRequest(id));
    }

    @Override
    @PostMapping("/{id}/diagnosticar")
    public ResponseEntity<Void> moverEmDiagnostico(@PathVariable UUID id) {
        this.moverEmDiagnosticoUseCase.handle(id);
        return ResponseEntity.ok().build();
    }

    @Override
    @PostMapping("/{id}/aguardando-aprovacao")
    public ResponseEntity<Void> moverAguardandoAprovacao(@PathVariable UUID id) {
        this.alterarStatusOsAguardandoAprovacaoUseCase.handle(id);
        return ResponseEntity.ok().build();
    }

    @Override
    @PostMapping("/{id}/executar")
    public ResponseEntity<Void> moverEmExecucao(@PathVariable UUID id) {
        this.moverEmExecucaoUseCase.handle(id);
        return ResponseEntity.ok().build();
    }

    @Override
    @PostMapping("/{id}/finalizar")
    public ResponseEntity<Void> moverFinalizada(@PathVariable UUID id) {
        this.moverFinalizadaUseCase.handle(id);
        return ResponseEntity.ok().build();
    }

    @Override
    @PostMapping("/{id}/aguardando-retirada")
    public ResponseEntity<Void> moverAguardandoRetirada(@PathVariable UUID id) {
        this.moverAguardandoRetiradaUseCase.handle(id);
        return ResponseEntity.ok().build();
    }

    @Override
    @PostMapping("/{id}/entregar")
    public ResponseEntity<Void> moverEntregue(@PathVariable UUID id) {
        this.moverEntregueUseCase.handle(id);
        return ResponseEntity.ok().build();
    }

    @Override
    @PostMapping("/{id}/ralizar-diagnostico")
    public ResponseEntity<Void> realizarDiagnostico(@PathVariable UUID id, @Valid @RequestBody RealizarDiagnosticoOrdemServicoCommand cmd) {
        cmd.setOrdemServicoId(id);
        this.realizarDiagnosticoUseCase.handle(cmd);
        return ResponseEntity.ok().build();
    }
}