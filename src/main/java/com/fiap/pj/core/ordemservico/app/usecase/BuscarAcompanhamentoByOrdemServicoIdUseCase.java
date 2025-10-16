package com.fiap.pj.core.ordemservico.app.usecase;

import com.fiap.pj.infra.ordemservico.controller.request.BuscarAcompanhamentoByOrdemServicoIdRequest;
import com.fiap.pj.infra.ordemservico.controller.response.AcompanhamentoOrdemServicoResponse;

import java.util.Optional;

public interface BuscarAcompanhamentoByOrdemServicoIdUseCase {

    Optional<AcompanhamentoOrdemServicoResponse> handle(BuscarAcompanhamentoByOrdemServicoIdRequest request);

}