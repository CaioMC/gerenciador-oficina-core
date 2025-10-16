package com.fiap.pj.core.orcamento.app.gateways;

import com.fiap.pj.core.orcamento.domain.Orcamento;
import com.fiap.pj.infra.orcamento.controller.request.ListarOrcamentoRequest;
import com.fiap.pj.infra.orcamento.controller.response.OrcamentoResponse;
import com.fiap.pj.infra.sk.api.Slice;
import com.fiap.pj.infra.sk.jpa.BaseRepositoryGateway;

public interface OrcamentoGateway extends BaseRepositoryGateway<Orcamento> {

    Slice<OrcamentoResponse> listar(ListarOrcamentoRequest request);


}
