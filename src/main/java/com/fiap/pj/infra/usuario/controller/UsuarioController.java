package com.fiap.pj.infra.usuario.controller;

import com.fiap.pj.core.usuario.app.usecase.AlterarUsuarioUseCase;
import com.fiap.pj.core.usuario.app.usecase.CriarUsuarioUseCase;
import com.fiap.pj.core.usuario.app.usecase.ExcluirUsuarioUseCase;
import com.fiap.pj.core.usuario.app.usecase.ListarUsuarioUseCase;
import com.fiap.pj.core.usuario.app.usecase.command.AlterarUsuarioCommand;
import com.fiap.pj.core.usuario.app.usecase.command.CriarUsuarioCommand;
import com.fiap.pj.core.usuario.app.usecase.command.ExcluirUsuarioCommand;
import com.fiap.pj.infra.sk.api.Slice;
import com.fiap.pj.infra.sk.web.ResponseEntityUtils;
import com.fiap.pj.infra.sk.web.ResponseEntityUtils.ResponseId;
import com.fiap.pj.infra.usuario.controller.openapi.UsuarioControllerOpenApi;
import com.fiap.pj.infra.usuario.controller.request.ListarUsuarioRequest;
import com.fiap.pj.infra.usuario.controller.response.UsuarioReponse;
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
@RequestMapping(path = UsuarioController.PATH)
@AllArgsConstructor
public class UsuarioController implements UsuarioControllerOpenApi {

    public static final String PATH = "v1/usuarios";

    private final CriarUsuarioUseCase criarUsuarioUseCase;
    private final AlterarUsuarioUseCase alterarUsuarioUseCase;
    private final ListarUsuarioUseCase listarUsuarioUseCase;
    private final ExcluirUsuarioUseCase excluirUsuarioUseCase;

    @PostMapping
    public ResponseEntity<ResponseId> criarUsuario(@Valid @RequestBody CriarUsuarioCommand cmd) {
        var usuarioId = criarUsuarioUseCase.handle(cmd);
        return ResponseEntityUtils.create(getClass(), usuarioId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> alteraUsuario(@Valid @PathVariable UUID id, @RequestBody AlterarUsuarioCommand cmd) {
        cmd.setId(id);
        alterarUsuarioUseCase.handle(cmd);
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping
    public Slice<UsuarioReponse> listarUsuario(@ParameterObject ListarUsuarioRequest filterRequest, @ParameterObject Pageable pageable) {
        filterRequest.setPageable(pageable);
        return listarUsuarioUseCase.handle(filterRequest);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable UUID id) {
        excluirUsuarioUseCase.handle(new ExcluirUsuarioCommand(id));
        return ResponseEntity.ok().build();
    }
}
