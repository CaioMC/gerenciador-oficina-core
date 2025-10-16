package com.fiap.pj.infra.orcamento.gateways;

import com.fiap.pj.core.orcamento.domain.OrcamentoItemPecaInsumo;
import com.fiap.pj.infra.orcamento.persistence.OrcamentoItemPecaInsumoEntity;
import lombok.experimental.UtilityClass;

import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class OrcamentoItemPecaInsumoRepositoryMapper {

    public static OrcamentoItemPecaInsumoEntity mapToTable(OrcamentoItemPecaInsumo domain) {
        return OrcamentoItemPecaInsumoEntity.builder()
                .id(domain.getId())
                .pecasInsumosId(domain.getPecasInsumosId())
                .orcamentoId(domain.getOrcamentoId())
                .descricao(domain.getDescricao())
                .valorUnitario(domain.getValorUnitario())
                .quantidade(domain.getQuantidade())
                .build();
    }

    public static OrcamentoItemPecaInsumo mapToDomain(OrcamentoItemPecaInsumoEntity entity) {
        return OrcamentoItemPecaInsumo.builder()
                .id(entity.getId())
                .pecasInsumosId(entity.getPecasInsumosId())
                .orcamentoId(entity.getOrcamentoId())
                .descricao(entity.getDescricao())
                .valorUnitario(entity.getValorUnitario())
                .quantidade(entity.getQuantidade())
                .build();
    }


    public static Set<OrcamentoItemPecaInsumo> mapToDomainSet(Set<OrcamentoItemPecaInsumoEntity> entities) {
        return entities.stream()
                .map(OrcamentoItemPecaInsumoRepositoryMapper::mapToDomain)
                .collect(Collectors.toSet());
    }

    public static Set<OrcamentoItemPecaInsumoEntity> mapToTableSet(Set<OrcamentoItemPecaInsumo> domains) {
        return domains.stream()
                .map(OrcamentoItemPecaInsumoRepositoryMapper::mapToTable)
                .collect(Collectors.toSet());
    }
}
