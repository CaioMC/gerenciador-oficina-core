package com.fiap.pj.infra.veiculo.gateways;

import com.fiap.pj.core.veiculo.domain.Veiculo;
import com.fiap.pj.infra.veiculo.persistence.VeiculoEntity;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class VeiculoRepositoryMapper {

    private VeiculoRepositoryMapper() {
    }

    public static Set<VeiculoEntity> mapToTable(Collection<Veiculo> veiculos) {
        return veiculos.stream().map(VeiculoRepositoryMapper::mapToTable).collect(Collectors.toSet());
    }

    public static VeiculoEntity mapToTable(Veiculo veiculo) {
        return new VeiculoEntity(veiculo.getId(), veiculo.getPlaca(), veiculo.getModelo(),
                veiculo.getMarca(), veiculo.getAno(), veiculo.getClienteId());
    }

    public static Veiculo mapToDomain(VeiculoEntity entity) {
        return new Veiculo(entity.getId(), entity.getPlaca(), entity.getModelo(),
                entity.getMarca(), entity.getAno(), entity.getClienteId());
    }

    public static Set<Veiculo> mapToDomain(Collection<VeiculoEntity> entitys) {
        return entitys.stream().map(VeiculoRepositoryMapper::mapToDomain).collect(Collectors.toSet());
    }
}
