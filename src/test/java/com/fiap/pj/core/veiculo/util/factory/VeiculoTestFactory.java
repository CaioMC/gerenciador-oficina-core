package com.fiap.pj.core.veiculo.util.factory;

import com.fiap.pj.core.veiculo.app.usecase.command.AdicionarVeiculoClienteCommand;
import com.fiap.pj.core.veiculo.domain.Veiculo;

import java.util.UUID;

public class VeiculoTestFactory {

    public static final UUID ID = UUID.randomUUID();
    public static final String PLACA = "MOI-0037";
    public static final String MARCA = "FIAT";
    public static final String MODELO = "FIAT 147";
    public static final int ANO = 2025;

    public static final UUID ID_ALTERADO = UUID.randomUUID();
    public static final String PLACA_ALTERADA = "CUA-1H50";
    public static final String MARCA_ALTERADA = "VOLKSWAGEN";
    public static final String MODELO_ALTERADO = "FUSCAL 1600";
    public static final int ANO_ALTERADO = 1989;

    public static Veiculo umVeiculo(UUID clienteId) {
        return new Veiculo(ID, PLACA, MODELO, MARCA, ANO, clienteId);
    }

    public static Veiculo umVeiculoAlterado(UUID clienteId) {
        return new Veiculo(ID_ALTERADO, PLACA_ALTERADA, MODELO_ALTERADO, MARCA_ALTERADA, ANO_ALTERADO, clienteId);
    }

    public static AdicionarVeiculoClienteCommand umAdicionarVeiculoClienteCommand(UUID clienteId) {
        return new AdicionarVeiculoClienteCommand(clienteId, PLACA, MARCA, MODELO, ANO);
    }
}
