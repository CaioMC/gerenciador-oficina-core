package com.fiap.pj.core.pecainsumo.app.usecase;

import com.fiap.pj.core.pecainsumo.app.usecase.command.CriarPecaInsumoCommand;
import com.fiap.pj.core.pecainsumo.domain.PecaInsumo;

public interface CriarPecaInsumoUseCase {

    PecaInsumo handle(CriarPecaInsumoCommand cmd);
} 