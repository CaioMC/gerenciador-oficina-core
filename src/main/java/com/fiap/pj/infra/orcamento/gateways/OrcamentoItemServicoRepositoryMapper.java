package com.fiap.pj.infra.orcamento.gateways;

import com.fiap.pj.core.orcamento.domain.OrcamentoItemServico;
import com.fiap.pj.infra.orcamento.persistence.OrcamentoItemServicoEntity;
import lombok.experimental.UtilityClass;

import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class OrcamentoItemServicoRepositoryMapper {


    public static OrcamentoItemServicoEntity mapToTable(OrcamentoItemServico domain) {
        return OrcamentoItemServicoEntity.builder()
                .id(domain.getId())
                .servicoId(domain.getServicoId())
                .orcamentoId(domain.getOrcamentoId())
                .descricao(domain.getDescricao())
                .valorUnitario(domain.getValorUnitario())
                .quantidade(domain.getQuantidade())
                .build();
    }

    public static OrcamentoItemServico mapToDomain(OrcamentoItemServicoEntity entity) {
        return OrcamentoItemServico.builder()
                .id(entity.getId())
                .servicoId(entity.getServicoId())
                .orcamentoId(entity.getOrcamentoId())
                .descricao(entity.getDescricao())
                .valorUnitario(entity.getValorUnitario())
                .quantidade(entity.getQuantidade())
                .build();
    }

    public static Set<OrcamentoItemServico> mapToDomainSet(Set<OrcamentoItemServicoEntity> entities) {
        return entities.stream()
                .map(OrcamentoItemServicoRepositoryMapper::mapToDomain)
                .collect(Collectors.toSet());
    }

    public static Set<OrcamentoItemServicoEntity> mapToTableSet(Set<OrcamentoItemServico> domains) {
        return domains.stream()
                .map(OrcamentoItemServicoRepositoryMapper::mapToTable)
                .collect(Collectors.toSet());
    }
}
