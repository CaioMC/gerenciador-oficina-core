package com.fiap.pj.infra.servico.controller;

import com.fiap.pj.core.servico.app.usecase.AlterarServicoUseCase;
import com.fiap.pj.core.servico.app.usecase.AtivarServicoUserCase;
import com.fiap.pj.core.servico.app.usecase.CriarServicoUseCase;
import com.fiap.pj.core.servico.app.usecase.ExcluirServicoUserCase;
import com.fiap.pj.core.servico.app.usecase.InativarServicoUserCase;
import com.fiap.pj.core.servico.app.usecase.ListarServicoUseCase;
import com.fiap.pj.core.servico.app.usecase.command.AlterarServicoCommand;
import com.fiap.pj.core.servico.app.usecase.command.AtivarServicoCommand;
import com.fiap.pj.core.servico.app.usecase.command.CriarServicoCommand;
import com.fiap.pj.core.servico.app.usecase.command.ExcluirServicoCommand;
import com.fiap.pj.core.servico.app.usecase.command.InativarServicoCommand;
import com.fiap.pj.infra.servico.controller.openapi.ServicoControllerOpenApi;
import com.fiap.pj.infra.servico.controller.request.ListarServicoRequest;
import com.fiap.pj.infra.servico.controller.response.ServicoResponse;
import com.fiap.pj.infra.sk.api.Slice;
import com.fiap.pj.infra.sk.web.ResponseEntityUtils;
import com.fiap.pj.infra.sk.web.ResponseEntityUtils.ResponseId;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = ServicoController.PATH)
@AllArgsConstructor
public class ServicoController implements ServicoControllerOpenApi {

    public static final String PATH = "v1/servicos";

    private final CriarServicoUseCase criarServicoUseCase;
    private final ListarServicoUseCase listarServicoUseCase;
    private final AtivarServicoUserCase ativarServicoUserCase;
    private final InativarServicoUserCase inativarServicoUserCase;
    private final AlterarServicoUseCase alterarServicoUseCase;
    private final ExcluirServicoUserCase excluirServicoUserCase;

    @Override
    @PostMapping
    public ResponseEntity<ResponseId> criarServico(@Valid @RequestBody CriarServicoCommand cmd) {
        var service = criarServicoUseCase.handle(cmd);
        return ResponseEntityUtils.create(getClass(), service.getId());
    }

    @Override
    @PostMapping("/{id}/inativar")
    public ResponseEntity<Void> inativarServico(@Valid @PathVariable UUID id) {
        inativarServicoUserCase.handle(new InativarServicoCommand(id));
        return ResponseEntity.ok().build();
    }

    @Override
    @PostMapping("/{id}/ativar")
    public ResponseEntity<Void> ativarServico(@Valid @PathVariable UUID id) {
        ativarServicoUserCase.handle(new AtivarServicoCommand(id));
        return ResponseEntity.ok().build();
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Void> alterarServico(@Valid @PathVariable UUID id, @RequestBody AlterarServicoCommand cmd) {
        cmd.setId(id);
        alterarServicoUseCase.handle(cmd);
        return ResponseEntity.ok().build();
    }


    @Override
    @GetMapping
    public Slice<ServicoResponse> listarServico(@ParameterObject ListarServicoRequest filterRequest, @ParameterObject Pageable pageable) {
        filterRequest.setPageable(pageable);
        return listarServicoUseCase.handle(filterRequest);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirServico(@PathVariable UUID id) {
        excluirServicoUserCase.handle(new ExcluirServicoCommand(id));
        return ResponseEntity.ok().build();
    }

}
