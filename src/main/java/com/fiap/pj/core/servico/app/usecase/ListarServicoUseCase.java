package com.fiap.pj.core.servico.app.usecase;

import com.fiap.pj.infra.servico.controller.request.ListarServicoRequest;
import com.fiap.pj.infra.servico.controller.response.ServicoResponse;
import com.fiap.pj.infra.sk.api.Slice;

public interface ListarServicoUseCase {

    Slice<ServicoResponse> handle(ListarServicoRequest request);
}
