package com.fiap.pj.infra.orcamento.persistence;

import com.fiap.pj.core.orcamento.domain.enums.OrcamentoStatus;
import com.fiap.pj.infra.cliente.persistence.ClienteEntity;
import com.fiap.pj.infra.veiculo.persistence.VeiculoEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

@Entity
@Table(name = "orcamentos")
@NoArgsConstructor
@Getter
public class OrcamentoEntity {

    @Id
    private UUID id;
    private String descricao;
    private UUID clienteId;
    private UUID veiculoId;
    private UUID usuarioId;
    private int hodometro;
    @Enumerated(EnumType.STRING)
    private OrcamentoStatus status;
    private UUID ordemServicoId;
    private ZonedDateTime dataCriacao;

    @OneToOne
    @JoinColumn(name = "clienteId", referencedColumnName = "id", insertable = false, updatable = false)
    private ClienteEntity cliente;

    @OneToOne
    @JoinColumn(name = "veiculoId", referencedColumnName = "id", insertable = false, updatable = false)
    private VeiculoEntity veiculo;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "orcamentoId")
    private Set<OrcamentoItemServicoEntity> servicos = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "orcamentoId")
    private Set<OrcamentoItemPecaInsumoEntity> pecasInsumos = new HashSet<>();

    @Builder
    public OrcamentoEntity(UUID id, String descricao, UUID clienteId, UUID veiculoId, UUID usuarioId, int hodometro, OrcamentoStatus status, UUID ordemServicoId, ZonedDateTime dataCriacao,
                           Set<OrcamentoItemServicoEntity> servicos, Set<OrcamentoItemPecaInsumoEntity> pecasInsumos) {
        this.id = requireNonNull(id);
        this.descricao = descricao;
        this.clienteId = requireNonNull(clienteId);
        this.veiculoId = requireNonNull(veiculoId);
        this.usuarioId = requireNonNull(usuarioId);
        this.hodometro = hodometro;
        this.status = requireNonNull(status);
        this.ordemServicoId = ordemServicoId;
        this.dataCriacao = requireNonNull(dataCriacao);
        this.servicos = servicos;
        this.pecasInsumos = pecasInsumos;
    }

    public BigDecimal getValorTotal() {
        BigDecimal totalPecasInsumo = this.getPecasInsumos().stream().map(OrcamentoItemPecaInsumoEntity::valorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
        return this.getServicos().stream().map(OrcamentoItemServicoEntity::valorTotal).reduce(BigDecimal.ZERO, BigDecimal::add).add(totalPecasInsumo);
    }

}
