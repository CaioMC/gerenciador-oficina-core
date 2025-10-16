package com.fiap.pj.core.ordemservico.app.usecase.command;

import java.util.UUID;

public record CriarOrdemServicoCommand(
        UUID clienteId,
        UUID veiculoId
) {
}