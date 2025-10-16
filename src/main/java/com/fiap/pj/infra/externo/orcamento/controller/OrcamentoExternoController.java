package com.fiap.pj.infra.externo.orcamento.controller;

import com.fiap.pj.core.externo.orcamento.usecase.AprovarOrcamentoExternoUseCase;
import com.fiap.pj.core.externo.orcamento.usecase.ReprovarOrcamentoExternoUseCase;
import com.fiap.pj.infra.externo.orcamento.controller.openapi.OrcamentoExternoControllerOpenApi;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = OrcamentoExternoController.PATH)
@AllArgsConstructor
@Tag(name = "orcamento-externo-controller", description = "API externa de orçamentos")
public class OrcamentoExternoController implements OrcamentoExternoControllerOpenApi {

    public static final String PATH = "externo/orcamentos";

    private final AprovarOrcamentoExternoUseCase aprovarOrcamentoExternoUseCase;
    private final ReprovarOrcamentoExternoUseCase reprovarOrcamentoExternoUseCase;

    @PostMapping("/{id}/aprovar")
    public ResponseEntity<Void> aprovarOrcamento(@PathVariable UUID id) {
        this.aprovarOrcamentoExternoUseCase.handle(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/reprovar")
    public ResponseEntity<Void> reprovarOrcamento(@PathVariable UUID id) {
        this.reprovarOrcamentoExternoUseCase.handle(id);
        return ResponseEntity.ok().build();
    }
}
