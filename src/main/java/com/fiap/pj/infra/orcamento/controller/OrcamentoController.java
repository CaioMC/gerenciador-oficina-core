package com.fiap.pj.infra.orcamento.controller;

import com.fiap.pj.core.orcamento.app.usecase.AlterarOrcamentoUseCase;
import com.fiap.pj.core.orcamento.app.usecase.AprovarOrcamentoUseCase;
import com.fiap.pj.core.orcamento.app.usecase.CriarOrcamentoUseCase;
import com.fiap.pj.core.orcamento.app.usecase.ListarOrcamentoUseCase;
import com.fiap.pj.core.orcamento.app.usecase.ReprovarOrcamentoUseCase;
import com.fiap.pj.core.orcamento.app.usecase.command.AlterarOrcamentoCommand;
import com.fiap.pj.core.orcamento.app.usecase.command.AprovarOrcamentoCommand;
import com.fiap.pj.core.orcamento.app.usecase.command.CriarOrcamentoCommand;
import com.fiap.pj.core.orcamento.app.usecase.command.ReprovarOrcamentoCommand;
import com.fiap.pj.infra.orcamento.controller.openapi.OrcamentoControllerOpenApi;
import com.fiap.pj.infra.orcamento.controller.request.ListarOrcamentoRequest;
import com.fiap.pj.infra.orcamento.controller.response.OrcamentoResponse;
import com.fiap.pj.infra.sk.api.Slice;
import com.fiap.pj.infra.sk.web.ResponseEntityUtils;
import com.fiap.pj.infra.sk.web.ResponseEntityUtils.ResponseId;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = OrcamentoController.PATH)
@AllArgsConstructor
public class OrcamentoController implements OrcamentoControllerOpenApi {

    public static final String PATH = "v1/orcamentos";

    private final CriarOrcamentoUseCase criarOrcamentoUseCase;
    private final AprovarOrcamentoUseCase aprovarOrcamentoUseCase;
    private final ReprovarOrcamentoUseCase reprovarOrcamentoUseCase;
    private final AlterarOrcamentoUseCase alterarOrcamentoUseCase;
    private final ListarOrcamentoUseCase listarOrcamentoUseCase;

    @Override
    @PostMapping
    public ResponseEntity<ResponseId> criarOrcamento(@Valid @RequestBody CriarOrcamentoCommand cmd) {
        var orcamento = this.criarOrcamentoUseCase.handle(cmd);
        return ResponseEntityUtils.create(getClass(), orcamento.getId());
    }

    @Override
    @PostMapping("/{id}/reprovar")
    public ResponseEntity<Void> reprovarOrcamento(@Valid @PathVariable UUID id) {
        this.reprovarOrcamentoUseCase.handle(new ReprovarOrcamentoCommand(id));
        return ResponseEntity.ok().build();
    }

    @Override
    @PostMapping("/{id}/aprovar")
    public ResponseEntity<Void> aprovarOrcamento(@Valid @PathVariable UUID id) {
        this.aprovarOrcamentoUseCase.handle(new AprovarOrcamentoCommand(id));
        return ResponseEntity.ok().build();
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Void> alterarOrcamento(@PathVariable UUID id, @RequestBody AlterarOrcamentoCommand cmd) {
        cmd.setId(id);
        this.alterarOrcamentoUseCase.handle(cmd);
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping
    public Slice<OrcamentoResponse> listarOrcamento(ListarOrcamentoRequest filterRequest, Pageable pageable) {
        filterRequest.setPageable(pageable);
        return listarOrcamentoUseCase.handle(filterRequest);
    }
}
