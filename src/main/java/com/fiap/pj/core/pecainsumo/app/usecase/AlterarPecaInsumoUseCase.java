package com.fiap.pj.core.pecainsumo.app.usecase;

import com.fiap.pj.core.pecainsumo.app.usecase.command.AlterarPecaInsumoCommand;

public interface AlterarPecaInsumoUseCase {

    void handle(AlterarPecaInsumoCommand cmd);
} 