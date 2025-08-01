package com.fiap.pj.core.usuario.domain;


import com.fiap.pj.infra.jpa.BaseCrudRepository;

import java.util.UUID;


public interface UsuarioDomainRepository extends BaseCrudRepository<Usuario, UUID> {

    @Override
    Usuario findByIdOrThrowNotFound(UUID id);


}
