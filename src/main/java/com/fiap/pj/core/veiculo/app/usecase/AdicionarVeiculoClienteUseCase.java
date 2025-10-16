package com.fiap.pj.core.veiculo.app.usecase;

import com.fiap.pj.core.veiculo.app.usecase.command.AdicionarVeiculoClienteCommand;
import com.fiap.pj.core.veiculo.domain.Veiculo;

public interface AdicionarVeiculoClienteUseCase {

    Veiculo handle(AdicionarVeiculoClienteCommand cmd);
}
