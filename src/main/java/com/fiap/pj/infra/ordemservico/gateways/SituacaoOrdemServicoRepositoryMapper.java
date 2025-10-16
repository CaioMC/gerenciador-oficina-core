package com.fiap.pj.infra.ordemservico.gateways;


import com.fiap.pj.core.ordemservico.domain.SituacaoOrdemServico;
import com.fiap.pj.infra.ordemservico.persistence.SituacaoOrdemServicoEntity;
import lombok.experimental.UtilityClass;

import java.util.Set;


@UtilityClass
public class SituacaoOrdemServicoRepositoryMapper {

    public static SituacaoOrdemServicoEntity mapToTable(SituacaoOrdemServico situacao) {
        return new SituacaoOrdemServicoEntity(
                situacao.getId(),
                situacao.getStatus(),
                situacao.getUsuarioId(),
                situacao.getOrdemServicoId(),
                situacao.getDataCriacao()
        );
    }


    public static SituacaoOrdemServico mapToDomain(SituacaoOrdemServicoEntity entity) {
        return new SituacaoOrdemServico(
                entity.getId(),
                entity.getStatus(),
                entity.getUsuarioId(),
                entity.getOrdemServicoId(),
                entity.getDataCriacao()
        );
    }

    public static Set<SituacaoOrdemServicoEntity> mapToTableSet(Set<SituacaoOrdemServico> situacoes) {
        return situacoes.stream().map(SituacaoOrdemServicoRepositoryMapper::mapToTable).collect(java.util.stream.Collectors.toSet());
    }

    public static Set<SituacaoOrdemServico> mapToDomainSet(Set<SituacaoOrdemServicoEntity> entities) {
        return entities.stream().map(SituacaoOrdemServicoRepositoryMapper::mapToDomain).collect(java.util.stream.Collectors.toSet());
    }

}
