package com.fiap.pj.infra.ordemservico.controller.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus;
import com.fiap.pj.infra.orcamento.controller.response.OrcamentoResponse.ClienteResponse;
import com.fiap.pj.infra.orcamento.controller.response.OrcamentoResponse.VeiculoResponse;
import com.fiap.pj.infra.orcamento.persistence.OrcamentoEntity;
import com.fiap.pj.infra.orcamento.persistence.OrcamentoItemPecaInsumoEntity;
import com.fiap.pj.infra.orcamento.persistence.OrcamentoItemServicoEntity;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.UUID;

@JsonPropertyOrder({"id", "cliente", "veiculo", "usuario", "status", "diagnostico", "servicos", "pecasInsumos", "valorTotal", "dataCriacao", "dataConclusao"})
public interface OrdemServicoResponse {

    UUID getId();

    ClienteResponse getCliente();

    VeiculoResponse getVeiculo();

    UsuarioResponse getUsuario();

    OrdemServicoStatus getStatus();

    DiagnosticoResponse getDiagnostico();

    ZonedDateTime getDataCriacao();

    ZonedDateTime getDataConclusao();


    @JsonIgnore
    Collection<OrcamentoEntity> getOrcamentos();

    default Collection<ItensResponse> getServicos() {
        return this.getOrcamentos().stream().map(OrcamentoEntity::getServicos)
                .flatMap(Collection::stream)
                .map(ItensResponse::new).toList();
    }

    default Collection<ItensResponse> getPecasInsumos() {
        return this.getOrcamentos().stream().map(OrcamentoEntity::getPecasInsumos)
                .flatMap(Collection::stream)
                .map(ItensResponse::new).toList();
    }

    default BigDecimal getValorTotal() {
        return this.getOrcamentos().stream()
                .map(OrcamentoEntity::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    interface UsuarioResponse {

        UUID getId();

        String getNome();
    }


    interface DiagnosticoResponse {
        UUID getId();

        String getDescricao();

        ZonedDateTime getDataCriacao();
    }

    @JsonPropertyOrder({"id", "descricao", "valorUnitario", "quantidade"})
    record ItensResponse
            (UUID id,
             String descricao,
             BigDecimal valorUnitario,
             int getQuantidade) {

        ItensResponse(OrcamentoItemServicoEntity servico) {
            this(servico.getId(), servico.getDescricao(), servico.getValorUnitario(), servico.getQuantidade());
        }

        ItensResponse(OrcamentoItemPecaInsumoEntity pecaInsumo) {
            this(pecaInsumo.getId(), pecaInsumo.getDescricao(), pecaInsumo.getValorUnitario(), pecaInsumo.getQuantidade());
        }
    }
}