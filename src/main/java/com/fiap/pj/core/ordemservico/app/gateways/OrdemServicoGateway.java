package com.fiap.pj.core.ordemservico.app.gateways;

import com.fiap.pj.core.ordemservico.domain.OrdemServico;
import com.fiap.pj.infra.ordemservico.controller.request.ListarOrdemServicoRequest;
import com.fiap.pj.infra.ordemservico.controller.response.AcompanhamentoOrdemServicoResponse;
import com.fiap.pj.infra.ordemservico.controller.response.OrdemServicoResponse;
import com.fiap.pj.infra.sk.api.Slice;
import com.fiap.pj.infra.sk.jpa.BaseRepositoryGateway;

import java.util.Optional;
import java.util.UUID;

public interface OrdemServicoGateway extends BaseRepositoryGateway<OrdemServico> {

    Slice<OrdemServicoResponse> listar(ListarOrdemServicoRequest request);

    Optional<AcompanhamentoOrdemServicoResponse> buscarAcompanhamentoByOrdemServicoId(UUID ordemServicoId);


}
