package com.fiap.pj.core.ordemservico.app.usecase;

import com.fiap.pj.infra.ordemservico.controller.request.ListarOrdemServicoRequest;
import com.fiap.pj.infra.ordemservico.controller.response.OrdemServicoResponse;
import com.fiap.pj.infra.sk.api.Slice;

public interface ListarOrdemServicoUseCase {

    Slice<OrdemServicoResponse> handle(ListarOrdemServicoRequest request);

}