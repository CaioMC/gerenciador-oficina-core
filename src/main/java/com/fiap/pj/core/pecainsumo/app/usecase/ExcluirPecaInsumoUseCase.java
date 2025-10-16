package com.fiap.pj.core.pecainsumo.app.usecase;

import com.fiap.pj.core.pecainsumo.app.usecase.command.ExcluirPecaInsumoCommand;

public interface ExcluirPecaInsumoUseCase {

    void handle(ExcluirPecaInsumoCommand cmd);
} 