package com.fiap.pj.infra.usuario.persistence;


import com.fiap.pj.infra.sk.jpa.ExtendedRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.UUID;


public interface UsuarioRepositoryJpa extends Repository<UsuarioEntity, UUID>, ExtendedRepository<UsuarioEntity, UUID>, JpaSpecificationExecutor<UsuarioEntity> {


    Optional<UsuarioEntity> findByEmail(String email);

}