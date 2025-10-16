package com.fiap.pj.infra.ordemservico.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "diagnosticos")
@NoArgsConstructor
@Getter
public class DiagnosticoEntity {

    @Id
    private UUID id;
    private String descricao;
    private UUID ordemServicoId;
    private ZonedDateTime dataCriacao;

    public DiagnosticoEntity(
            UUID id,
            UUID ordemServicoId,
            String descricao, ZonedDateTime dataCriacao
    ) {
        this.id = id;
        this.ordemServicoId = ordemServicoId;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
    }

}