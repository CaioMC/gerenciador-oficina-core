package com.fiap.pj.infra.cliente.persistence;


import com.fiap.pj.core.sk.documentoidentificacao.domain.DocumentoIdentificacao;
import com.fiap.pj.infra.veiculo.persistence.VeiculoEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

@Entity
@Table(name = "clientes")
@NoArgsConstructor
@Getter
public class ClienteEntity {

    @Id
    private UUID id;
    private String nome;
    private String email;
    private String telefone;
    private boolean ativo;
    private String endereco;

    @Embedded
    private DocumentoIdentificacao documentoIdentificacao;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "clienteId")
    private Set<VeiculoEntity> veiculos = new HashSet<>();

    public ClienteEntity(UUID id, String nome, String email, String telefone, boolean ativo, String endereco, DocumentoIdentificacao documentoIdentificacao, Set<VeiculoEntity> veiculos) {
        this.id = requireNonNull(id);
        this.nome = requireNonNull(nome);
        this.email = email;
        this.telefone = telefone;
        this.ativo = ativo;
        this.endereco = requireNonNull(endereco);
        this.documentoIdentificacao = requireNonNull(documentoIdentificacao);
        this.veiculos = veiculos;
    }

}
