package com.fiap.pj.core.veiculo.exception;

public class VeiculoExceptions {
    private VeiculoExceptions() {
    }

    public static class VeiculoNaoEntradoException extends RuntimeException {
        public VeiculoNaoEntradoException() {
            super("Veículo não encontrado.");
        }
    }

    public static class VeiculoNaoPertenceAoClienteException extends RuntimeException {
        public VeiculoNaoPertenceAoClienteException() {
            super("Veículo não pertence ao Cliente informado.");
        }
    }

    public static class VeiucloPlacaDuplicadaException extends RuntimeException {
        public VeiucloPlacaDuplicadaException() {
            super("Placa do veículo já cadastrada.");
        }
    }
}
