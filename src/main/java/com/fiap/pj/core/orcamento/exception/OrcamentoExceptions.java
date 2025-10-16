package com.fiap.pj.core.orcamento.exception;

public class OrcamentoExceptions {
    private OrcamentoExceptions() {
    }

    public static class OrcamentoNaoEncontradoException extends RuntimeException {
        public OrcamentoNaoEncontradoException() {
            super("Orcamento não encontrado.");
        }
    }

    public static class ReprovarOrcamentoStatusInvalidoException extends RuntimeException {
        public ReprovarOrcamentoStatusInvalidoException() {
            super("Status do orcamento não permite reprovar.");
        }
    }

    public static class AlterarOrcamentoStatusInvalidoException extends RuntimeException {
        public AlterarOrcamentoStatusInvalidoException() {
            super("Status do orcamento não permite alterar.");
        }
    }


}
