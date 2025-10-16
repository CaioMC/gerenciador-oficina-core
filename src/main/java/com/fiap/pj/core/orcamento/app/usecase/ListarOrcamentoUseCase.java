package com.fiap.pj.core.orcamento.app.usecase;

import com.fiap.pj.infra.orcamento.controller.request.ListarOrcamentoRequest;
import com.fiap.pj.infra.orcamento.controller.response.OrcamentoResponse;
import com.fiap.pj.infra.sk.api.Slice;

public interface ListarOrcamentoUseCase {

    Slice<OrcamentoResponse> handle(ListarOrcamentoRequest request);
}
