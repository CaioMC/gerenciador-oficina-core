package com.fiap.pj.infra.orcamento.controller.response;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fiap.pj.core.orcamento.domain.enums.OrcamentoStatus;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.UUID;

@JsonPropertyOrder({"id", "descricao", "cliente", "veiculo", "hodometro", "status", "servicos", "pecasInsumos", "valorTotal", "dataCriacao"})
public interface OrcamentoResponse {

    UUID getId();

    String getDescricao();


    ClienteResponse getCliente();

    VeiculoResponse getVeiculo();

    Collection<OrcamentoServicoResponse> getServicos();

    Collection<OrcamentoPecaInsumoResponse> getPecasInsumos();

    int getHodometro();

    OrcamentoStatus getStatus();

    ZonedDateTime getDataCriacao();

    BigDecimal getValorTotal();

    interface ClienteResponse {

        UUID getId();

        String getNome();
    }

    interface VeiculoResponse {

        String getId();

        String getPlaca();

        String getModelo();

        String getMarca();

        Integer getAno();
    }

    @JsonPropertyOrder({"id", "descricao", "valorUnitario", "quantidade"})
    interface OrcamentoServicoResponse {

        UUID getId();

        String getDescricao();

        BigDecimal getValorUnitario();

        BigDecimal getQuantidade();
    }

    @JsonPropertyOrder({"id", "descricao", "valorUnitario", "quantidade"})
    interface OrcamentoPecaInsumoResponse {

        UUID getId();

        String getDescricao();

        BigDecimal getValorUnitario();

        BigDecimal getQuantidade();
    }

}


