package com.fiap.pj.infra.servico.controller.response;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;

@JsonPropertyOrder({"id", "descricao", "valorUnitario", "observacao", "ativo"})
public interface ServicoResponse {

    String getId();

    String getDescricao();

    BigDecimal getValorUnitario();

    String getObservacao();

    boolean isAtivo();

}
