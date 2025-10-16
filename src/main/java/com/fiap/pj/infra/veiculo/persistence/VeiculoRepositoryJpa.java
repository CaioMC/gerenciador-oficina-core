package com.fiap.pj.infra.veiculo.persistence;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;


public interface VeiculoRepositoryJpa extends JpaRepository<VeiculoEntity, UUID> {


    @Query("SELECT CASE WHEN COUNT(v) > 0 THEN TRUE ELSE FALSE END FROM VeiculoEntity v WHERE v.placa = :placa")
    boolean existsByPlaca(@Param("placa") String placa);

}