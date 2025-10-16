package com.fiap.pj.core.pecainsumo.exception;

public class PecaInsumoExceptions {
    private PecaInsumoExceptions() {
    }

    public static class PecaInsumoNaoEncontradoException extends RuntimeException {
        public PecaInsumoNaoEncontradoException() {
            super("Peça/Insumo não encontrado.");
        }
    }

    public static class PecaInsumoComRelacionamentoException extends RuntimeException {
        public PecaInsumoComRelacionamentoException() {
            super("Peça/Insumo com relacionamento, não permite excluir.");
        }
    }

    public static class PecasInsumoQuantidadeMenorIgualAZeroException extends RuntimeException {
        public PecasInsumoQuantidadeMenorIgualAZeroException() {
            super("Quantidade de Peça/Insumo deve ser maior que zero");
        }
    }

    public static class PecasInsumoQuantidadeEstoqueInsuficienteException extends RuntimeException {
        public PecasInsumoQuantidadeEstoqueInsuficienteException() {
            super("Quantidade em estoque insuficiente.");
        }
    }
} 