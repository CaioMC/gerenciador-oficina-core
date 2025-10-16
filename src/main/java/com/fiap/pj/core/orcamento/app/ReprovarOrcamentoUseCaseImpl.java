package com.fiap.pj.core.orcamento.app;

import com.fiap.pj.core.cliente.app.gateways.ClienteGateway;
import com.fiap.pj.core.cliente.domain.Cliente;
import com.fiap.pj.core.cliente.exception.ClienteExceptions.ClienteNaoEncontradoException;
import com.fiap.pj.core.email.app.usecase.EnviarEmailUseCase;
import com.fiap.pj.core.email.app.usecase.command.EnviarEmailCommand;
import com.fiap.pj.core.email.domain.enums.Template;
import com.fiap.pj.core.orcamento.app.gateways.OrcamentoGateway;
import com.fiap.pj.core.orcamento.app.usecase.ReprovarOrcamentoUseCase;
import com.fiap.pj.core.orcamento.app.usecase.command.ReprovarOrcamentoCommand;
import com.fiap.pj.core.orcamento.domain.Orcamento;
import com.fiap.pj.core.orcamento.exception.OrcamentoExceptions.OrcamentoNaoEncontradoException;
import com.fiap.pj.core.pecainsumo.app.gateways.PecaInsumoGateway;
import com.fiap.pj.core.servico.app.gateways.ServicoGateway;

import java.util.List;


public class ReprovarOrcamentoUseCaseImpl extends OrcamentoUseCaseImpl implements ReprovarOrcamentoUseCase {

    private final OrcamentoGateway orcamentoGateway;
    private final EnviarEmailUseCase enviarEmailUseCase;
    private final ClienteGateway clienteGateway;

    public ReprovarOrcamentoUseCaseImpl(
            ServicoGateway servicoGateway,
            PecaInsumoGateway pecaInsumoGateway, OrcamentoGateway orcamentoGateway,
            EnviarEmailUseCase enviarEmailUseCase, ClienteGateway clienteGateway
    ) {
        super(servicoGateway, pecaInsumoGateway);
        this.orcamentoGateway = orcamentoGateway;
        this.enviarEmailUseCase = enviarEmailUseCase;
        this.clienteGateway = clienteGateway;
    }

    @Override
    public void handle(ReprovarOrcamentoCommand cmd) {
        Orcamento orcamento = this.orcamentoGateway.buscarPorId(cmd.id()).orElseThrow(OrcamentoNaoEncontradoException::new);
        orcamento.reprovar();

        super.roolbackPecasInsumos(orcamento);

        this.orcamentoGateway.salvar(orcamento);

        Cliente cliente = this.clienteGateway.buscarPorId(orcamento.getClienteId()).orElseThrow(ClienteNaoEncontradoException::new);

        this.enviarEmailUseCase.handle(
                new EnviarEmailCommand(
                        cliente.getEmail(),
                        Template.ORCAMENTO_RECUSADO,
                        List.of(orcamento.getId())
                )
        );
    }
}
