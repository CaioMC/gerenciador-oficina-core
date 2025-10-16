package com.fiap.pj.core.externo.orcamento.exception;

public class OrcamentoExternoExceptions {
    private OrcamentoExternoExceptions() {
    }

    public static class OrcamentoTempoLimiteExcedidoException extends RuntimeException {
        public OrcamentoTempoLimiteExcedidoException() {
            super("O tempo limite para aprovação do orçamento foi excedido.");
        }
    }

}
