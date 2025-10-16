package com.fiap.pj.core.ordemservico.util.factory;

import com.fiap.pj.core.cliente.util.factory.ClienteTestFactory;
import com.fiap.pj.core.ordemservico.app.usecase.command.CriarOrdemServicoCommand;
import com.fiap.pj.core.ordemservico.app.usecase.command.RealizarDiagnosticoOrdemServicoCommand;
import com.fiap.pj.core.ordemservico.domain.OrdemServico;
import com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus;
import com.fiap.pj.core.usuario.util.factrory.UsuarioTestFactory;
import com.fiap.pj.core.veiculo.util.factory.VeiculoTestFactory;

import java.util.UUID;

public class OrdemServicoTestFactory {

    public static final UUID ID = UUID.randomUUID();
    public static final String DESCRICAO = "Diagnostico XPTO";


    public static OrdemServico umaOrdemServico(OrdemServicoStatus status) {
        return OrdemServico.builder()
                .id(ID)
                .usuarioId(UsuarioTestFactory.ID)
                .veiculoId(VeiculoTestFactory.ID)
                .status(status)
                .clienteId(ClienteTestFactory.ID).build();

    }

    public static CriarOrdemServicoCommand umCriarOrdemServicoCommand() {
        return new CriarOrdemServicoCommand(ClienteTestFactory.ID, VeiculoTestFactory.ID);
    }

    public static RealizarDiagnosticoOrdemServicoCommand umRealizarDiagnosticoOrdemServicoCommand() {
        return new RealizarDiagnosticoOrdemServicoCommand(ID, DESCRICAO);
    }
}
