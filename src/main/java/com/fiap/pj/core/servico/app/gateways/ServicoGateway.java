package com.fiap.pj.core.servico.app.gateways;

import com.fiap.pj.core.servico.domain.Servico;
import com.fiap.pj.infra.servico.controller.request.ListarServicoRequest;
import com.fiap.pj.infra.servico.controller.response.ServicoResponse;
import com.fiap.pj.infra.sk.api.Slice;
import com.fiap.pj.infra.sk.jpa.BaseRepositoryGateway;

public interface ServicoGateway extends BaseRepositoryGateway<Servico> {

    Slice<ServicoResponse> listarServico(ListarServicoRequest request);


}
