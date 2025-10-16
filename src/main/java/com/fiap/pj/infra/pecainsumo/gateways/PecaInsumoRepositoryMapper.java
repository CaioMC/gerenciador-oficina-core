package com.fiap.pj.infra.pecainsumo.gateways;

import com.fiap.pj.core.pecainsumo.domain.PecaInsumo;
import com.fiap.pj.infra.pecainsumo.persistence.PecaInsumoEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PecaInsumoRepositoryMapper {

    public static PecaInsumoEntity mapToTable(PecaInsumo pecaInsumo) {
        return new PecaInsumoEntity(
                pecaInsumo.getId(),
                pecaInsumo.getDescricao(),
                pecaInsumo.getModeloVeiculo(),
                pecaInsumo.getValorUnitario(),
                pecaInsumo.getQuantidadeEstoque(),
                pecaInsumo.getQuantidadeMinimoEstoque()
        );
    }

    public static PecaInsumo mapToDomain(PecaInsumoEntity entity) {
        return new PecaInsumo(
                entity.getId(),
                entity.getDescricao(),
                entity.getModeloVeiculo(),
                entity.getValorUnitario(),
                entity.getQuantidadeEstoque(),
                entity.getQuantidadeMinimoEstoque()
        );
    }
}
