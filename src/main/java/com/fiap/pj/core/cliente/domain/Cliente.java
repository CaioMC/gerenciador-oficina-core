package com.fiap.pj.core.cliente.domain;


import com.fiap.pj.core.sk.documentoidentificacao.domain.DocumentoIdentificacao;
import com.fiap.pj.core.veiculo.domain.Veiculo;
import com.fiap.pj.core.veiculo.exception.VeiculoExceptions.VeiculoNaoPertenceAoClienteException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static java.util.Objects.requireNonNull;


@Getter
@AllArgsConstructor
public class Cliente {

    private UUID id;
    private String nome;
    private String email;
    private String telefone;
    private boolean ativo;
    private String endereco;
    private DocumentoIdentificacao documentoIdentificacao;
    private Set<Veiculo> veiculos = new HashSet<>();

    @Builder
    public Cliente(UUID id, String nome, String email, String telefone, boolean ativo, String endereco, DocumentoIdentificacao documentoIdentificacao) {
        this.id = requireNonNull(id);
        this.nome = requireNonNull(nome);
        this.email = email;
        this.telefone = telefone;
        this.ativo = ativo;
        this.endereco = requireNonNull(endereco);
        this.documentoIdentificacao = requireNonNull(documentoIdentificacao);
    }

    public void ativar() {
        this.ativo = true;
    }

    public void inativar() {
        this.ativo = false;
    }

    public void alterar(String name, String email, String endereco, String telefone) {
        this.nome = name;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public void adicionarVeiculo(Veiculo veiculo) {
        this.veiculos.add(veiculo);
    }

    public void validarVeiculo(UUID veiculoId) {
        if (this.getVeiculos().stream().noneMatch(veiculo -> veiculo.getId().equals(veiculoId))) {
            throw new VeiculoNaoPertenceAoClienteException();
        }
    }
}
