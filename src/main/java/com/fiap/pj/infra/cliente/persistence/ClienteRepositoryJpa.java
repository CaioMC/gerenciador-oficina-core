package com.fiap.pj.infra.cliente.persistence;


import com.fiap.pj.infra.sk.jpa.ExtendedRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;


public interface ClienteRepositoryJpa extends JpaRepository<ClienteEntity, UUID>, ExtendedRepository<ClienteEntity, UUID> {

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM ClienteEntity c WHERE c.documentoIdentificacao.numero = :numero")
    boolean existsByDocumentoIdentificacaoNumero(@Param("numero") String numero);

}