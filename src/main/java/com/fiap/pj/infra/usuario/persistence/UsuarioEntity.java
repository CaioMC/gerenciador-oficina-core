package com.fiap.pj.infra.usuario.persistence;

import com.fiap.pj.core.usuario.domain.enums.Perfil;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@Getter
public class UsuarioEntity implements Serializable {

    private static final long serialVersionUID = -2059235872181567136L;

    @ElementCollection
    @CollectionTable(name = "usuarios_perfil", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "perfil")
    private Set<Perfil> perfis = new HashSet<>();

    @Id
    private UUID id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String sobreNome;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    @Setter
    private String senha;
    @Column(nullable = false)
    private boolean ativo;

    public UsuarioEntity(UUID id, String nome, String sobreNome, String email, String senha, boolean ativo, Set<Perfil> perfis) {
        this.id = requireNonNull(id);
        this.nome = requireNonNull(nome);
        this.sobreNome = sobreNome;
        this.email = requireNonNull(email);
        this.senha = requireNonNull(senha);
        this.ativo = ativo;
        this.perfis = requireNonNull(perfis);
    }

    public String getNomeCompleto() {
        return this.nome + " " + this.sobreNome;
    }

}