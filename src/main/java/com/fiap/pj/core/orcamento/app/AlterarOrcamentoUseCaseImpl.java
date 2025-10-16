package com.fiap.pj.core.orcamento.app;

import com.fiap.pj.core.cliente.app.gateways.ClienteGateway;
import com.fiap.pj.core.cliente.domain.Cliente;
import com.fiap.pj.core.cliente.exception.ClienteExceptions.ClienteNaoEncontradoException;
import com.fiap.pj.core.orcamento.app.gateways.OrcamentoGateway;
import com.fiap.pj.core.orcamento.app.usecase.AlterarOrcamentoUseCase;
import com.fiap.pj.core.orcamento.app.usecase.command.AlterarOrcamentoCommand;
import com.fiap.pj.core.orcamento.domain.Orcamento;
import com.fiap.pj.core.orcamento.exception.OrcamentoExceptions.OrcamentoNaoEncontradoException;
import com.fiap.pj.core.pecainsumo.app.gateways.PecaInsumoGateway;
import com.fiap.pj.core.servico.app.gateways.ServicoGateway;

public class AlterarOrcamentoUseCaseImpl extends OrcamentoUseCaseImpl implements AlterarOrcamentoUseCase {

    private final OrcamentoGateway orcamentoGateway;
    private final ClienteGateway clienteGateway;

    public AlterarOrcamentoUseCaseImpl(
            ServicoGateway servicoGateway,
            PecaInsumoGateway pecaInsumoGateway,
            OrcamentoGateway orcamentoGateway,
            ClienteGateway clienteGateway
    ) {
        super(servicoGateway, pecaInsumoGateway);

        this.orcamentoGateway = orcamentoGateway;
        this.clienteGateway = clienteGateway;

    }

    @Override
    public void handle(AlterarOrcamentoCommand cmd) {
        Orcamento orcamento = this.orcamentoGateway.buscarPorId(cmd.getId()).orElseThrow(OrcamentoNaoEncontradoException::new);
        Cliente cliente = clienteGateway.buscarPorId(cmd.getClienteId()).orElseThrow(ClienteNaoEncontradoException::new);

        cliente.validarVeiculo(cmd.getVeiculoId());

        orcamento.alterar(cmd.getDescricao(), cmd.getClienteId(), cmd.getVeiculoId(), cmd.getHodometro());

        super.buildItemServico(orcamento, cmd.getServicos());
        super.buildItemPecaInsumo(orcamento, cmd.getPecasInsumos());

        this.orcamentoGateway.salvar(orcamento);
    }
}