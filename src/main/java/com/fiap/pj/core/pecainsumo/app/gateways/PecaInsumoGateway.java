package com.fiap.pj.core.pecainsumo.app.gateways;

import com.fiap.pj.core.pecainsumo.domain.PecaInsumo;
import com.fiap.pj.infra.pecainsumo.controller.request.ListarPecaInsumoRequest;
import com.fiap.pj.infra.pecainsumo.controller.response.PecaInsumoResponse;
import com.fiap.pj.infra.sk.api.Slice;
import com.fiap.pj.infra.sk.jpa.BaseRepositoryGateway;

public interface PecaInsumoGateway extends BaseRepositoryGateway<PecaInsumo> {

    Slice<PecaInsumoResponse> listar(ListarPecaInsumoRequest request);


}
