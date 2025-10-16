package com.fiap.pj.core.usuario.domain;

import com.fiap.pj.core.usuario.domain.enums.Perfil;
import com.fiap.pj.core.util.CollectionUtils;
import lombok.Getter;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

@Getter
public class Usuario implements Serializable {

    private static final long serialVersionUID = -2059235872181567136L;

    private final Set<Perfil> perfis = new HashSet<>();

    private UUID id;
    private String nome;
    private String sobreNome;
    private String email;
    private String senha;
    private boolean ativo;

    public Usuario(UUID id, String nome, String sobreNome, String email, String senha, boolean ativo) {
        this.id = requireNonNull(id);
        this.nome = requireNonNull(nome);
        this.sobreNome = sobreNome;
        this.email = requireNonNull(email);
        this.senha = requireNonNull(senha);
        this.ativo = ativo;
    }

    public void alterar(String nome, String sobreNome, boolean ativo, String senha, Set<Perfil> perfis) {
        this.nome = requireNonNull(nome);
        this.sobreNome = sobreNome;
        this.ativo = ativo;
        this.senha = requireNonNull(senha);

        this.alterarPerfis(perfis);
    }

    public void adicionarPerfils(Collection<Perfil> perfis) {
        this.perfis.addAll(perfis);
    }

    public void alterarPerfis(Set<Perfil> perfis) {
        CollectionUtils.instanceNonNullCollection(this.perfis, perfis);
    }
}