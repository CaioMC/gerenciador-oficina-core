package com.fiap.pj.core.pecainsumo.app.usecase;

import com.fiap.pj.infra.pecainsumo.controller.request.ListarPecaInsumoRequest;
import com.fiap.pj.infra.pecainsumo.controller.response.PecaInsumoResponse;
import com.fiap.pj.infra.sk.api.Slice;

public interface ListarPecaInsumoUseCase {

    Slice<PecaInsumoResponse> handle(ListarPecaInsumoRequest filterRequest);
} 