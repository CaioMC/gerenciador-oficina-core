package com.fiap.pj.core.veiculo.app.usecase.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap.pj.infra.util.validator.Plate;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AdicionarVeiculoClienteCommand(@JsonIgnore UUID clienteId,
                                             @Plate
                                             String placa,
                                             String marca,
                                             String modelo,
                                             @NotNull(message = "O ano não pode ser nulo.")
                                             @Min(value = 1900, message = "O ano deve ser maior ou igual a 1900.")
                                             @Max(value = 2100, message = "O ano deve ser menor ou igual a 2100.")
                                             int ano) {

    public AdicionarVeiculoClienteCommand comClienteId(UUID clienteId) {
        return new AdicionarVeiculoClienteCommand(clienteId, this.placa, this.marca, this.modelo, this.ano);
    }
}
