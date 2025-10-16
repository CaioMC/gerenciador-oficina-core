package com.fiap.pj.infra.ordemservico.gateways;

import com.fiap.pj.core.ordemservico.domain.OrdemServico;
import com.fiap.pj.infra.orcamento.gateways.OrcamentoRepositoryMapper;
import com.fiap.pj.infra.ordemservico.persistence.OrdemServicoEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrdemServicoRepositoryMapper {

    public static OrdemServicoEntity mapToTable(OrdemServico ordemServico) {
        return OrdemServicoEntity.builder()
                .id(ordemServico.getId())
                .clienteId(ordemServico.getClienteId())
                .veiculoId(ordemServico.getVeiculoId())
                .usuarioId(ordemServico.getUsuarioId())
                .status(ordemServico.getStatus())
                .dataCriacao(ordemServico.getDataCriacao())
                .dataConclusao(ordemServico.getDataConclusao())
                .diagnostico(DiagnosticoRepositoryMapper.mapToTable(ordemServico.getDiagnostico()))
                .historicoSituacao(SituacaoOrdemServicoRepositoryMapper.mapToTableSet(ordemServico.getHistoricoSituacao()))
                .orcamentos(OrcamentoRepositoryMapper.mapToTableSet(ordemServico.getOrcamentos()))
                .build();
    }

    public static OrdemServico mapToDomain(OrdemServicoEntity entity) {
        return new OrdemServico(entity.getId(),
                entity.getClienteId(),
                entity.getVeiculoId(),
                entity.getUsuarioId(),
                entity.getStatus(),
                entity.getDataCriacao(),
                entity.getDataConclusao(),
                DiagnosticoRepositoryMapper.mapToDomain(entity.getDiagnostico()),
                SituacaoOrdemServicoRepositoryMapper.mapToDomainSet(entity.getHistoricoSituacao()),
                OrcamentoRepositoryMapper.mapToDomainSet(entity.getOrcamentos())
        );

    }

}
