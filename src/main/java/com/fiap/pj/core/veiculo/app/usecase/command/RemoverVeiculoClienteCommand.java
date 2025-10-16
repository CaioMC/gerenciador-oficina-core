package com.fiap.pj.core.veiculo.app.usecase.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RemoverVeiculoClienteCommand(@JsonIgnore UUID clienteId,
                                           @NotNull(message = "O ID do veículo não pode ser nulo.")
                                           UUID veiculoId) {

    public RemoverVeiculoClienteCommand comClienteId(UUID clienteId) {
        return new RemoverVeiculoClienteCommand(clienteId, this.veiculoId);
    }
} 