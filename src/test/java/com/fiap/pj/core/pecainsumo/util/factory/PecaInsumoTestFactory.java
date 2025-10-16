package com.fiap.pj.core.pecainsumo.util.factory;

import com.fiap.pj.core.pecainsumo.app.usecase.command.AlterarPecaInsumoCommand;
import com.fiap.pj.core.pecainsumo.app.usecase.command.CriarPecaInsumoCommand;
import com.fiap.pj.core.pecainsumo.domain.PecaInsumo;

import java.math.BigDecimal;
import java.util.UUID;

public class PecaInsumoTestFactory {

    public static final UUID ID = UUID.randomUUID();
    public static final String MODELO_VEICULO = "FUSCA 1600";
    public static final String DESCRICAO = "Óleo do Motor";
    public static final BigDecimal VALOR_UNITARIO = new BigDecimal("45.50");
    public static final Integer QUANTIDADE_ESTOQUE = 10;
    public static final Integer QUANTIDADE_MINIMO_ESTOQUE = 5;

    public static final String MODELO_VEICULO_ALTERADO = "Fiat 147";
    public static final String DESCRICAO_ALTERADA = "Filtro de ar";
    public static final BigDecimal VALOR_UNITARIO_ALTERADO = new BigDecimal("25.00");
    public static final Integer QUANTIDADE_ESTOQUE_ALTERADO = 15;
    public static final Integer QUANTIDADE_MINIMO_ESTOQUE_ALTERADO = 3;

    public static PecaInsumo umPecaInsumo() {
        return new PecaInsumo(ID, DESCRICAO, MODELO_VEICULO, VALOR_UNITARIO, QUANTIDADE_ESTOQUE, QUANTIDADE_MINIMO_ESTOQUE);
    }

    public static CriarPecaInsumoCommand umCriarPecaInsumoCommand() {
        return new CriarPecaInsumoCommand(DESCRICAO, MODELO_VEICULO, VALOR_UNITARIO, QUANTIDADE_ESTOQUE, QUANTIDADE_MINIMO_ESTOQUE);
    }

    public static AlterarPecaInsumoCommand umAlterarPecaInsumoCommand(UUID id) {
        return new AlterarPecaInsumoCommand(DESCRICAO_ALTERADA, MODELO_VEICULO_ALTERADO, VALOR_UNITARIO_ALTERADO, QUANTIDADE_ESTOQUE_ALTERADO, QUANTIDADE_MINIMO_ESTOQUE_ALTERADO, id);
    }
} 