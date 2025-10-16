package com.fiap.pj.core.servico.domain;


import com.fiap.pj.infra.sk.jpa.BaseCrudRepository;

import java.util.UUID;

public interface ServicoDomainRepository extends BaseCrudRepository<Servico, UUID> {

    @Override
    Servico findByIdOrThrowNotFound(UUID id);
}
