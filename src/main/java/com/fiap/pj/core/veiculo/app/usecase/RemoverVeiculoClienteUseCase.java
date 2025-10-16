package com.fiap.pj.core.veiculo.app.usecase;

import com.fiap.pj.core.veiculo.app.usecase.command.RemoverVeiculoClienteCommand;

public interface RemoverVeiculoClienteUseCase {
    void handle(RemoverVeiculoClienteCommand cmd);
} 