package com.fiap.pj.infra.ordemservico.persistence;

import com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus;
import com.fiap.pj.infra.cliente.persistence.ClienteEntity;
import com.fiap.pj.infra.orcamento.persistence.OrcamentoEntity;
import com.fiap.pj.infra.usuario.persistence.UsuarioEntity;
import com.fiap.pj.infra.veiculo.persistence.VeiculoEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "ordens_servico")
@NoArgsConstructor
@Getter
public class OrdemServicoEntity {

    @Id
    private UUID id;
    private UUID clienteId;
    private UUID veiculoId;
    private UUID usuarioId;
    @Enumerated(EnumType.STRING)
    private OrdemServicoStatus status;
    private ZonedDateTime dataCriacao;
    private ZonedDateTime dataConclusao;

    @OneToOne
    @JoinColumn(name = "clienteId", referencedColumnName = "id", insertable = false, updatable = false)
    private ClienteEntity cliente;

    @OneToOne
    @JoinColumn(name = "veiculoId", referencedColumnName = "id", insertable = false, updatable = false)
    private VeiculoEntity veiculo;

    @OneToOne
    @JoinColumn(name = "usuarioId", referencedColumnName = "id", insertable = false, updatable = false)
    private UsuarioEntity usuario;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "diagnosticoId", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private DiagnosticoEntity diagnostico;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ordemServicoId")
    @OrderBy("dataCriacao DESC")
    private Set<SituacaoOrdemServicoEntity> historicoSituacao = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "ordemServicoId", insertable = false, updatable = false)
    private Set<OrcamentoEntity> orcamentos = new HashSet<>();


    @Builder
    public OrdemServicoEntity(UUID id, UUID clienteId, UUID veiculoId, UUID usuarioId, OrdemServicoStatus status, ZonedDateTime dataCriacao,
                              ZonedDateTime dataConclusao, ClienteEntity cliente, VeiculoEntity veiculo, UsuarioEntity usuario,
                              DiagnosticoEntity diagnostico, Set<SituacaoOrdemServicoEntity> historicoSituacao, Set<OrcamentoEntity> orcamentos) {
        this.id = id;
        this.clienteId = clienteId;
        this.veiculoId = veiculoId;
        this.usuarioId = usuarioId;
        this.status = status;
        this.dataCriacao = dataCriacao;
        this.dataConclusao = dataConclusao;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.usuario = usuario;
        this.diagnostico = diagnostico;
        this.historicoSituacao = historicoSituacao;
        this.orcamentos = orcamentos;
    }
}