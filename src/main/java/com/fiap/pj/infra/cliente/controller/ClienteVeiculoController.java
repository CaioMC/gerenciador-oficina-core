package com.fiap.pj.infra.cliente.controller;

import com.fiap.pj.core.veiculo.app.usecase.AdicionarVeiculoClienteUseCase;
import com.fiap.pj.core.veiculo.app.usecase.RemoverVeiculoClienteUseCase;
import com.fiap.pj.core.veiculo.app.usecase.command.AdicionarVeiculoClienteCommand;
import com.fiap.pj.core.veiculo.app.usecase.command.RemoverVeiculoClienteCommand;
import com.fiap.pj.infra.cliente.controller.openapi.ClienteVeiculoControllerOpenApi;
import com.fiap.pj.infra.sk.web.ResponseEntityUtils;
import com.fiap.pj.infra.sk.web.ResponseEntityUtils.ResponseId;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = ClienteVeiculoController.PATH)
@AllArgsConstructor
public class ClienteVeiculoController implements ClienteVeiculoControllerOpenApi {

    public static final String PATH = "v1/clientes/{id}/veiculos";

    private final AdicionarVeiculoClienteUseCase adicionarVeiculoClienteUseCase;
    private final RemoverVeiculoClienteUseCase removerVeiculoClienteUseCase;

    @Override
    @PostMapping
    public ResponseEntity<ResponseId> adicionarVeiculo(@PathVariable UUID id, @RequestBody AdicionarVeiculoClienteCommand cmd) {
        var veiculo = adicionarVeiculoClienteUseCase.handle(cmd.comClienteId(id));
        return ResponseEntityUtils.ok(veiculo.getId());
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> removerVeiculo(@PathVariable UUID id, @RequestBody RemoverVeiculoClienteCommand cmd) {
        removerVeiculoClienteUseCase.handle(cmd.comClienteId(id));
        return ResponseEntity.noContent().build();
    }

}
