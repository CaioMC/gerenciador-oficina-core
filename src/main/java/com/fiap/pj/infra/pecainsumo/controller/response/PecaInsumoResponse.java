package com.fiap.pj.infra.pecainsumo.controller.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;

@JsonPropertyOrder({"id", "descricao", "modeloVeiculo", "valorUnitario", "quantidadeEstoque", "quantidadeMinimoEstoque"})
public interface PecaInsumoResponse {

    String getId();

    String getModeloVeiculo();

    String getDescricao();

    BigDecimal getValorUnitario();

    int getQuantidadeEstoque();

    int getQuantidadeMinimoEstoque();
} 