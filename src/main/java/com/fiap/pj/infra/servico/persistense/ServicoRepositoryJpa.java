package com.fiap.pj.infra.servico.persistense;


import com.fiap.pj.infra.sk.jpa.ExtendedRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface ServicoRepositoryJpa extends JpaRepository<ServicoEntity, UUID>, ExtendedRepository<ServicoEntity, UUID> {


}