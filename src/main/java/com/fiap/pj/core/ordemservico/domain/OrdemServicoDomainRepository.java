package com.fiap.pj.core.ordemservico.domain;

import com.fiap.pj.infra.ordemservico.controller.response.AcompanhamentoOrdemServicoResponse;
import com.fiap.pj.infra.sk.jpa.BaseCrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrdemServicoDomainRepository extends BaseCrudRepository<OrdemServico, UUID> {

    @Override
    OrdemServico findByIdOrThrowNotFound(UUID id);

    Optional<AcompanhamentoOrdemServicoResponse> findById(UUID id,
                                                          Class<AcompanhamentoOrdemServicoResponse> type);
}