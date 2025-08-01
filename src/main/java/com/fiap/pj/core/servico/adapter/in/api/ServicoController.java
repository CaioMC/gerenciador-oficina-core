package com.fiap.pj.core.servico.adapter.in.api;

import com.fiap.pj.core.servico.adapter.in.api.openapi.ServicoControllerOpenApi;
import com.fiap.pj.core.servico.adapter.in.api.request.ListarServicoRequest;
import com.fiap.pj.core.servico.adapter.in.api.response.ServicoResponse;
import com.fiap.pj.core.servico.usecase.AtivarServicoUserCase;
import com.fiap.pj.core.servico.usecase.CriarServicoUseCase;
import com.fiap.pj.core.servico.usecase.InativarServicoUserCase;
import com.fiap.pj.core.servico.usecase.ListarServicoUseCase;
import com.fiap.pj.core.servico.usecase.command.AtivarServicoCommand;
import com.fiap.pj.core.servico.usecase.command.CriarServicoCommand;
import com.fiap.pj.core.servico.usecase.command.InativarServicoCommand;
import com.fiap.pj.core.sk.web.ResponseEntityUtils;
import com.fiap.pj.infra.api.Slice;
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

    @Override
    @PostMapping
    public ResponseEntity<Void> criarServico(@Valid @RequestBody CriarServicoCommand cmd) {
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
    @GetMapping
    public Slice<ServicoResponse> listarServico(@ParameterObject ListarServicoRequest filterRequest, @ParameterObject Pageable pageable) {
        filterRequest.setPageable(pageable);
        return listarServicoUseCase.handle(filterRequest);
    }

}
