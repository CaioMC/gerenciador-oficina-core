package com.fiap.pj.core.ordemservico.app;

import com.fiap.pj.core.cliente.app.gateways.ClienteGateway;
import com.fiap.pj.core.cliente.domain.Cliente;
import com.fiap.pj.core.cliente.exception.ClienteExceptions.ClienteNaoEncontradoException;
import com.fiap.pj.core.email.app.usecase.EnviarEmailUseCase;
import com.fiap.pj.core.email.app.usecase.command.EnviarEmailCommand;
import com.fiap.pj.core.email.domain.enums.Template;
import com.fiap.pj.core.ordemservico.app.gateways.OrdemServicoGateway;
import com.fiap.pj.core.ordemservico.app.usecase.MoverEmDiagnosticoUseCase;
import com.fiap.pj.core.ordemservico.domain.OrdemServico;
import com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus;
import com.fiap.pj.core.ordemservico.exception.OrdemServicoExceptions.OrdemServicoNaoEncontradaException;

import java.util.List;
import java.util.UUID;


public class MoverEmDiagnosticoUseCaseImpl implements MoverEmDiagnosticoUseCase {

    private final OrdemServicoGateway ordemServicoGateway;
    private final EnviarEmailUseCase enviarEmailUseCase;
    private final ClienteGateway clienteGateway;

    public MoverEmDiagnosticoUseCaseImpl(OrdemServicoGateway ordemServicoGateway, EnviarEmailUseCase enviarEmailUseCase, ClienteGateway clienteGateway) {
        this.ordemServicoGateway = ordemServicoGateway;
        this.enviarEmailUseCase = enviarEmailUseCase;
        this.clienteGateway = clienteGateway;
    }

    @Override
    public void handle(UUID id) {
        OrdemServico ordemServico = this.ordemServicoGateway.buscarPorId(id).orElseThrow(OrdemServicoNaoEncontradaException::new);
        ordemServico.moverEmDiagnostico();

        this.ordemServicoGateway.salvar(ordemServico);

        this.enviarEmail(ordemServico);
    }

    private void enviarEmail(OrdemServico ordemServico) {
        Cliente cliente = this.clienteGateway.buscarPorId(ordemServico.getClienteId()).orElseThrow(ClienteNaoEncontradoException::new);

        this.enviarEmailUseCase.handle(
                new EnviarEmailCommand(
                        cliente.getEmail(),
                        Template.ORDEM_SERVICO_MUDANCA_STATUS,
                        List.of(
                                ordemServico.getId().toString(),
                                OrdemServicoStatus.EM_DIAGNOSTICO
                        )
                )
        );
    }
}