package com.fiap.pj.infra.servico.gateways;

import com.fiap.pj.core.servico.domain.Servico;
import com.fiap.pj.infra.servico.persistense.ServicoEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ServicoRepositoryMapper {

    public static ServicoEntity mapToTable(Servico servico) {
        return new ServicoEntity(
                servico.getId(),
                servico.getDescricao(),
                servico.getValorUnitario(),
                servico.getObservacao(),
                servico.isAtivo()
        );
    }

    public static Servico mapToDomain(ServicoEntity entity) {
        return new Servico(
                entity.getId(),
                entity.getDescricao(),
                entity.getValorUnitario(),
                entity.getObservacao(),
                entity.isAtivo()
        );
    }
}
