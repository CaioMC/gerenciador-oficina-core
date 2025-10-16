package com.fiap.pj.core.orcamento.app;

import com.fiap.pj.core.cliente.app.gateways.ClienteGateway;
import com.fiap.pj.core.cliente.domain.Cliente;
import com.fiap.pj.core.cliente.exception.ClienteExceptions.ClienteNaoEncontradoException;
import com.fiap.pj.core.email.app.usecase.EnviarEmailUseCase;
import com.fiap.pj.core.email.app.usecase.command.EnviarEmailCommand;
import com.fiap.pj.core.email.domain.enums.Template;
import com.fiap.pj.core.orcamento.app.gateways.OrcamentoGateway;
import com.fiap.pj.core.orcamento.app.usecase.AprovarOrcamentoUseCase;
import com.fiap.pj.core.orcamento.app.usecase.command.AprovarOrcamentoCommand;
import com.fiap.pj.core.orcamento.domain.Orcamento;
import com.fiap.pj.core.orcamento.exception.OrcamentoExceptions.OrcamentoNaoEncontradoException;
import com.fiap.pj.core.ordemservico.app.usecase.CriarOrdemServicoUseCase;
import com.fiap.pj.core.ordemservico.app.usecase.command.CriarOrdemServicoCommand;

import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;


public class AprovarOrcamentoUseCaseImpl implements AprovarOrcamentoUseCase {

    private final OrcamentoGateway orcamentoGateway;
    private final CriarOrdemServicoUseCase criarOrdemServicoUseCase;
    private final EnviarEmailUseCase enviarEmailUseCase;
    private final ClienteGateway clienteGateway;

    public AprovarOrcamentoUseCaseImpl(OrcamentoGateway orcamentoGateway, CriarOrdemServicoUseCase criarOrdemServicoUseCase, EnviarEmailUseCase enviarEmailUseCase, ClienteGateway clienteGateway) {
        this.orcamentoGateway = orcamentoGateway;
        this.criarOrdemServicoUseCase = criarOrdemServicoUseCase;
        this.enviarEmailUseCase = enviarEmailUseCase;
        this.clienteGateway = clienteGateway;
    }

    @Override
    public void handle(AprovarOrcamentoCommand cmd) {
        Orcamento orcamento = this.orcamentoGateway.buscarPorId(cmd.id()).orElseThrow(OrcamentoNaoEncontradoException::new);
        orcamento.aprovar();

        if (isNull(orcamento.getOrdemServicoId())) {
            UUID ordemServicoId = this.criarOrdemServicoUseCase.handle(
                    new CriarOrdemServicoCommand(
                            orcamento.getClienteId(),
                            orcamento.getVeiculoId()
                    )
            );

            orcamento.vincularOrdemServico(ordemServicoId);
        }

        this.orcamentoGateway.salvar(orcamento);

        Cliente cliente = this.clienteGateway.buscarPorId(orcamento.getClienteId()).orElseThrow(ClienteNaoEncontradoException::new);

        this.enviarEmailUseCase.handle(
                new EnviarEmailCommand(
                        cliente.getEmail(),
                        Template.ORCAMENTO_APROVADO,
                        List.of(
                                orcamento.getId(),
                                orcamento.getOrdemServicoId()
                        )
                )
        );
    }
}