package com.fiap.pj.infra.ordemservico.persistence;


import com.fiap.pj.infra.ordemservico.controller.response.AcompanhamentoOrdemServicoResponse;
import com.fiap.pj.infra.sk.jpa.ExtendedRepository;
import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.UUID;


public interface OrdemServicoRepositoryJpa extends Repository<OrdemServicoEntity, UUID>, ExtendedRepository<OrdemServicoEntity, UUID> {

    Optional<AcompanhamentoOrdemServicoResponse> findById(UUID id,
                                                          Class<AcompanhamentoOrdemServicoResponse> type);
}