package com.fiap.pj.infra.orcamento.gateways;

import com.fiap.pj.core.orcamento.domain.Orcamento;
import com.fiap.pj.infra.orcamento.persistence.OrcamentoEntity;
import lombok.experimental.UtilityClass;

import java.util.Set;

@UtilityClass
public class OrcamentoRepositoryMapper {

    public static OrcamentoEntity mapToTable(Orcamento orcamento) {
        return OrcamentoEntity.builder().id(orcamento.getId())
                .descricao(orcamento.getDescricao())
                .clienteId(orcamento.getClienteId())
                .veiculoId(orcamento.getVeiculoId())
                .usuarioId(orcamento.getUsuarioId())
                .hodometro(orcamento.getHodometro())
                .status(orcamento.getStatus())
                .ordemServicoId(orcamento.getOrdemServicoId())
                .dataCriacao(orcamento.getDataCriacao())
                .servicos(OrcamentoItemServicoRepositoryMapper.mapToTableSet(orcamento.getServicos()))
                .pecasInsumos(OrcamentoItemPecaInsumoRepositoryMapper.mapToTableSet(orcamento.getPecasInsumos()))
                .build();
    }

    public static Orcamento mapToDomain(OrcamentoEntity entity) {
        var orcamento = Orcamento.builder().id(entity.getId())
                .descricao(entity.getDescricao())
                .clienteId(entity.getClienteId())
                .veiculoId(entity.getVeiculoId())
                .usuarioId(entity.getUsuarioId())
                .hodometro(entity.getHodometro())
                .status(entity.getStatus())
                .ordemServicoId(entity.getOrdemServicoId())
                .build();
        orcamento.setServicos(OrcamentoItemServicoRepositoryMapper.mapToDomainSet(entity.getServicos()));
        orcamento.setPecasInsumos(OrcamentoItemPecaInsumoRepositoryMapper.mapToDomainSet(entity.getPecasInsumos()));
        orcamento.setDataCriacao(entity.getDataCriacao());
        return orcamento;
    }

    public static Set<OrcamentoEntity> mapToTableSet(Set<Orcamento> orcamentos) {
        return orcamentos.stream().map(OrcamentoRepositoryMapper::mapToTable).collect(java.util.stream.Collectors.toSet());
    }

    public static Set<Orcamento> mapToDomainSet(Set<OrcamentoEntity> entities) {
        return entities.stream().map(OrcamentoRepositoryMapper::mapToDomain).collect(java.util.stream.Collectors.toSet());
    }

}
