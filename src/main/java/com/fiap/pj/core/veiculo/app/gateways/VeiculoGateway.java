package com.fiap.pj.core.veiculo.app.gateways;

import com.fiap.pj.core.veiculo.domain.Veiculo;
import com.fiap.pj.infra.sk.jpa.BaseRepositoryGateway;

public interface VeiculoGateway extends BaseRepositoryGateway<Veiculo> {

    boolean existsByPlaca(String placa);


}
