package com.fiap.pj.core.pecainsumo.domain;

import com.fiap.pj.core.pecainsumo.exception.PecaInsumoExceptions.PecasInsumoQuantidadeEstoqueInsuficienteException;
import com.fiap.pj.core.pecainsumo.exception.PecaInsumoExceptions.PecasInsumoQuantidadeMenorIgualAZeroException;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

import static java.util.Objects.requireNonNull;


@Getter
public class PecaInsumo {

    private UUID id;
    private String descricao;
    private BigDecimal valorUnitario;
    private Integer quantidadeEstoque;
    private Integer quantidadeMinimoEstoque;
    private String modeloVeiculo;


    public PecaInsumo(UUID id, String descricao, String modeloVeiculo, BigDecimal valorUnitario, Integer quantidadeEstoque, Integer quantidadeMinimoEstoque) {
        this.id = requireNonNull(id);
        this.modeloVeiculo = requireNonNull(modeloVeiculo);
        this.descricao = requireNonNull(descricao);
        this.valorUnitario = requireNonNull(valorUnitario);
        this.quantidadeEstoque = requireNonNull(quantidadeEstoque);
        this.quantidadeMinimoEstoque = requireNonNull(quantidadeMinimoEstoque);
    }

    public void adicionarEstoque(Integer quantidade) {
        if (quantidade <= 0) {
            throw new PecasInsumoQuantidadeMenorIgualAZeroException();
        }
        this.quantidadeEstoque += quantidade;
    }

    public void removerEstoque(Integer quantidade) {
        if (quantidade <= 0) {
            throw new PecasInsumoQuantidadeMenorIgualAZeroException();
        }
        if (this.quantidadeEstoque < quantidade) {
            throw new PecasInsumoQuantidadeEstoqueInsuficienteException();
        }
        this.quantidadeEstoque -= quantidade;
    }

    public void atualizarDados(String descricao, String modeloVeiculo, BigDecimal valorUnitario, Integer quantidadeMinimoEstoque) {
        this.modeloVeiculo = requireNonNull(modeloVeiculo);
        this.descricao = requireNonNull(descricao);
        this.valorUnitario = requireNonNull(valorUnitario);
        this.quantidadeMinimoEstoque = requireNonNull(quantidadeMinimoEstoque);
    }

    public boolean estoqueBaixo() {
        return this.quantidadeEstoque <= this.quantidadeMinimoEstoque;
    }
} 