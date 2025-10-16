package com.fiap.pj.infra.orcamento.persistence;


import com.fiap.pj.infra.sk.jpa.ExtendedRepository;
import org.springframework.data.repository.Repository;

import java.util.UUID;


public interface OrcamentoRepositoryJpa extends Repository<OrcamentoEntity, UUID>, ExtendedRepository<OrcamentoEntity, UUID> {


}