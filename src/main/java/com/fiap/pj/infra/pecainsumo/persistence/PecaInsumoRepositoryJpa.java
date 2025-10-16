package com.fiap.pj.infra.pecainsumo.persistence;

import com.fiap.pj.infra.sk.jpa.ExtendedRepository;
import org.springframework.data.repository.Repository;

import java.util.UUID;

public interface PecaInsumoRepositoryJpa extends Repository<PecaInsumoEntity, UUID>, ExtendedRepository<PecaInsumoEntity, UUID> {

} 