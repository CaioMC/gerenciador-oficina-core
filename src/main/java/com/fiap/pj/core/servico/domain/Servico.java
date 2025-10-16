package com.fiap.pj.core.servico.domain;


import com.fiap.pj.core.servico.exception.ServicoExceptions.StatusServicoNaoPermiteAlterarException;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

@Getter
public class Servico {


    private UUID id;
    private String descricao;
    private BigDecimal valorUnitario;
    private String observacao;
    private boolean ativo;

    public Servico(UUID id, String descricao, BigDecimal valorUnitario, String observacao, boolean ativo) {
        this.id = requireNonNull(id);
        this.descricao = requireNonNull(descricao);
        this.valorUnitario = valorUnitario;
        this.observacao = observacao;
        this.ativo = ativo;
    }

    public void ativar() {
        this.ativo = true;
    }

    public void inativar() {
        this.ativo = false;
    }

    public void alterar(String descricao, BigDecimal valorUnitario, String observacao) {
        if (!this.ativo) {
            throw new StatusServicoNaoPermiteAlterarException();
        }
        this.descricao = descricao;
        this.valorUnitario = valorUnitario;
        this.observacao = observacao;
    }
}
