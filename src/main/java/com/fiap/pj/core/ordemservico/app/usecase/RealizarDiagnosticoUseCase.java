package com.fiap.pj.core.ordemservico.app.usecase;

import com.fiap.pj.core.ordemservico.app.usecase.command.RealizarDiagnosticoOrdemServicoCommand;

public interface RealizarDiagnosticoUseCase {

    void handle(RealizarDiagnosticoOrdemServicoCommand cmd);

}