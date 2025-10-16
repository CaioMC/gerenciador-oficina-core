package com.fiap.pj.core.servico.util.factory;

import com.fiap.pj.core.servico.app.usecase.command.AlterarServicoCommand;
import com.fiap.pj.core.servico.app.usecase.command.CriarServicoCommand;
import com.fiap.pj.core.servico.domain.Servico;

import java.math.BigDecimal;
import java.util.UUID;

public class ServicoTestFactory {

    public static final UUID ID = UUID.randomUUID();
    public static final String DESCRICAO = "Troca de Óleo";
    public static final BigDecimal VALOR_UNITARIO = BigDecimal.valueOf(69.00);
    public static final String OBSERVACAO = "xpto";

    public static final String DESCRICAO_ALTERADA = "Alinhamento";
    public static final BigDecimal VALOR_UNITARIO_ALTERADO = BigDecimal.valueOf(11.00);
    public static final String OBSERVACAO_ALTERADA = "bico cortesia";

    public static Servico umServico() {
        return new Servico(ID, DESCRICAO, VALOR_UNITARIO, OBSERVACAO, true);
    }

    public static CriarServicoCommand umCriarServicoCommand() {
        return new CriarServicoCommand(DESCRICAO, VALOR_UNITARIO, OBSERVACAO);
    }

    public static AlterarServicoCommand umAlterarServicoCommand(UUID id) {
        return new AlterarServicoCommand(DESCRICAO_ALTERADA, VALOR_UNITARIO_ALTERADO, OBSERVACAO_ALTERADA, id);
    }

}
