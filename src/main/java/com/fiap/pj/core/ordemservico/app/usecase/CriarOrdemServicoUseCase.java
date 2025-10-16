package com.fiap.pj.core.ordemservico.app.usecase;

import com.fiap.pj.core.ordemservico.app.usecase.command.CriarOrdemServicoCommand;

import java.util.UUID;

public interface CriarOrdemServicoUseCase {

    UUID handle(CriarOrdemServicoCommand cmd);
}
