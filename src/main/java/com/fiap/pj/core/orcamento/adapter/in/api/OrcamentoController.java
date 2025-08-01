package com.fiap.pj.core.orcamento.adapter.in.api;

import com.fiap.pj.core.orcamento.adapter.in.api.openapi.OrcamentoControllerOpenApi;
import com.fiap.pj.core.orcamento.usecase.CriarOrcamentoUseCase;
import com.fiap.pj.core.orcamento.usecase.ReprovarOrcamentoUseCase;
import com.fiap.pj.core.orcamento.usecase.command.CriarOrcamentoCommand;
import com.fiap.pj.core.orcamento.usecase.command.ReprovarOrcamentoCommand;
import com.fiap.pj.core.sk.web.ResponseEntityUtils;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    private final ReprovarOrcamentoUseCase reprovarOrcamentoUseCase;


    @Override
    @PostMapping
    public ResponseEntity<Void> criarOrcamento(@Valid @RequestBody CriarOrcamentoCommand cmd) {
        var orcamento = criarOrcamentoUseCase.handle(cmd);
        return ResponseEntityUtils.create(getClass(), orcamento.getId());
    }

    @Override
    @PostMapping("/{id}/reprovar")
    public ResponseEntity<Void> reprovarOrcamento(@Valid @PathVariable UUID id) {
        reprovarOrcamentoUseCase.handle(new ReprovarOrcamentoCommand(id));
        return ResponseEntity.ok().build();
    }
}
