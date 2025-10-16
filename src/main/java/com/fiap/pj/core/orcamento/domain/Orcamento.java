package com.fiap.pj.core.orcamento.domain;

import com.fiap.pj.core.orcamento.domain.enums.OrcamentoStatus;
import com.fiap.pj.core.orcamento.exception.OrcamentoExceptions.AlterarOrcamentoStatusInvalidoException;
import com.fiap.pj.core.orcamento.exception.OrcamentoExceptions.ReprovarOrcamentoStatusInvalidoException;
import com.fiap.pj.core.pecainsumo.exception.PecaInsumoExceptions.PecasInsumoQuantidadeMenorIgualAZeroException;
import com.fiap.pj.core.util.DateTimeUtils;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static java.util.Objects.requireNonNull;


@Getter
public class Orcamento {

    @Id
    private UUID id;
    private String descricao;
    private UUID clienteId;
    private UUID veiculoId;
    private UUID usuarioId;
    private int hodometro;
    private OrcamentoStatus status;
    private UUID ordemServicoId;
    @Setter
    private ZonedDateTime dataCriacao;

    @Setter
    private Set<OrcamentoItemServico> servicos = new HashSet<>();
    @Setter
    private Set<OrcamentoItemPecaInsumo> pecasInsumos = new HashSet<>();

    @Builder
    public Orcamento(UUID id, String descricao, UUID clienteId, UUID veiculoId, UUID usuarioId, int hodometro, OrcamentoStatus status, UUID ordemServicoId) {
        this.id = requireNonNull(id);
        this.descricao = descricao;
        this.clienteId = requireNonNull(clienteId);
        this.veiculoId = requireNonNull(veiculoId);
        this.usuarioId = requireNonNull(usuarioId);
        this.hodometro = hodometro;
        this.status = requireNonNull(status);
        this.ordemServicoId = ordemServicoId;
        this.dataCriacao = DateTimeUtils.getNow();
    }

    public void alterar(String descricao, UUID clienteId, UUID veiculoId, int hodometro) {
        if (!this.status.isAguardandoAprovacao()) {
            throw new AlterarOrcamentoStatusInvalidoException();
        }
        this.descricao = descricao;
        this.clienteId = requireNonNull(clienteId);
        this.veiculoId = requireNonNull(veiculoId);
        this.hodometro = hodometro;
    }

    public void adicionarServico(OrcamentoItemServico servico) {
        this.servicos.add(servico);
    }

    public void adicionaPecaInsumo(OrcamentoItemPecaInsumo pecaInsumo) {
        if (pecaInsumo.getQuantidade() <= 0) {
            throw new PecasInsumoQuantidadeMenorIgualAZeroException();
        }

        this.pecasInsumos.add(pecaInsumo);
    }

    public void aprovar() {
        if (!this.status.isAguardandoAprovacao()) {
            throw new AlterarOrcamentoStatusInvalidoException();
        }
        this.status = OrcamentoStatus.APROVADO;
    }

    public void reprovar() {
        if (!this.status.isAguardandoAprovacao()) {
            throw new ReprovarOrcamentoStatusInvalidoException();
        }
        this.status = OrcamentoStatus.REPROVADO;
    }

    public void vincularOrdemServico(UUID ordemServicoId) {
        this.ordemServicoId = requireNonNull(ordemServicoId);
    }
}
