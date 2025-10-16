package com.fiap.pj.core.orcamento.app;


import com.fiap.pj.core.orcamento.app.usecase.command.OrcamentoItemPecaInsumoCommand;
import com.fiap.pj.core.orcamento.app.usecase.command.OrcamentoItemServicoCommand;
import com.fiap.pj.core.orcamento.domain.Orcamento;
import com.fiap.pj.core.orcamento.domain.OrcamentoItemPecaInsumo;
import com.fiap.pj.core.orcamento.domain.OrcamentoItemServico;
import com.fiap.pj.core.pecainsumo.app.gateways.PecaInsumoGateway;
import com.fiap.pj.core.pecainsumo.domain.PecaInsumo;
import com.fiap.pj.core.pecainsumo.exception.PecaInsumoExceptions.PecaInsumoNaoEncontradoException;
import com.fiap.pj.core.servico.app.gateways.ServicoGateway;
import com.fiap.pj.core.servico.domain.Servico;
import com.fiap.pj.core.servico.exception.ServicoExceptions.ServicoNaoEncontradoException;

import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.fiap.pj.core.util.CollectionUtils.nullSafeStream;


public abstract class OrcamentoUseCaseImpl {

    private final ServicoGateway servicoGateway;
    private final PecaInsumoGateway pecaInsumoGateway;

    protected OrcamentoUseCaseImpl(ServicoGateway servicoGateway, PecaInsumoGateway pecaInsumoGateway) {
        this.servicoGateway = servicoGateway;
        this.pecaInsumoGateway = pecaInsumoGateway;
    }

    protected void buildItemPecaInsumo(Orcamento orcamento, Set<OrcamentoItemPecaInsumoCommand> pecasInsumos) {
        Set<OrcamentoItemPecaInsumo> orcamentoItemPecaInsumoIdRemovidoSet = nullSafeStream(orcamento.getPecasInsumos())
                .filter(this.predicatePecasInsumosRemovidos(pecasInsumos)).collect(Collectors.toSet());

        orcamento.getPecasInsumos().removeAll(orcamentoItemPecaInsumoIdRemovidoSet);
        this.addQuantidadeRemovidaNoEstoque(orcamentoItemPecaInsumoIdRemovidoSet);

        nullSafeStream(pecasInsumos).forEach(cmd -> {
            PecaInsumo pecaInsumo = this.pecaInsumoGateway.buscarPorId(cmd.pecaInsumoId()).orElseThrow(PecaInsumoNaoEncontradoException::new);

            this.controleEstoquePecaInsumo(orcamento, cmd, pecaInsumo);

            this.pecaInsumoGateway.salvar(pecaInsumo);

            OrcamentoItemPecaInsumo pecaInsumoOrcamento = OrcamentoItemPecaInsumo
                    .builder()
                    .id(UUID.randomUUID())
                    .pecasInsumosId(pecaInsumo.getId())
                    .orcamentoId(orcamento.getId())
                    .quantidade(cmd.quantidade())
                    .descricao(pecaInsumo.getDescricao())
                    .valorUnitario(pecaInsumo.getValorUnitario())
                    .build();

            orcamento.adicionaPecaInsumo(pecaInsumoOrcamento);
        });
    }

    protected void buildItemServico(Orcamento orcamento, Set<OrcamentoItemServicoCommand> servicos) {
        orcamento.getServicos().clear();

        nullSafeStream(servicos).forEach(cmd -> {

            Servico servico = this.servicoGateway.buscarPorId(cmd.servicoId()).orElseThrow(ServicoNaoEncontradoException::new);

            OrcamentoItemServico servicoOrcamento = OrcamentoItemServico
                    .builder()
                    .id(UUID.randomUUID())
                    .orcamentoId(orcamento.getId())
                    .valorUnitario(servico.getValorUnitario())
                    .quantidade(cmd.quantidade())
                    .descricao(servico.getDescricao())
                    .servicoId(servico.getId())
                    .build();

            orcamento.adicionarServico(servicoOrcamento);
        });
    }

    protected void roolbackPecasInsumos(Orcamento orcamento) {
        nullSafeStream(orcamento.getPecasInsumos())
                .forEach(orcamentoItemPecaInsumo -> {
                    PecaInsumo pecaInsumo = this.pecaInsumoGateway.buscarPorId(orcamentoItemPecaInsumo.getPecasInsumosId()).orElseThrow(PecaInsumoNaoEncontradoException::new);
                    pecaInsumo.adicionarEstoque(orcamentoItemPecaInsumo.getQuantidade());
                    this.pecaInsumoGateway.salvar(pecaInsumo);
                });
    }

    private Predicate<OrcamentoItemPecaInsumo> predicatePecasInsumosRemovidos(Set<OrcamentoItemPecaInsumoCommand> pecasInsumos) {
        return item ->
                pecasInsumos
                        .stream()
                        .noneMatch(cmd -> cmd.pecaInsumoId().equals(item.getId()));
    }

    private void addQuantidadeRemovidaNoEstoque(Set<OrcamentoItemPecaInsumo> orcamentoItemPecaInsumoIdRemovidoSet) {
        orcamentoItemPecaInsumoIdRemovidoSet.forEach(orcamentoPecaInsumo -> {
            PecaInsumo pecaInsumo = this.pecaInsumoGateway.buscarPorId(orcamentoPecaInsumo.getPecasInsumosId()).orElseThrow(PecaInsumoNaoEncontradoException::new);
            pecaInsumo.adicionarEstoque(orcamentoPecaInsumo.getQuantidade());

            this.pecaInsumoGateway.salvar(pecaInsumo);
        });
    }

    private void controleEstoquePecaInsumo(Orcamento orcamento, OrcamentoItemPecaInsumoCommand cmd, PecaInsumo pecaInsumo) {
        orcamento.getPecasInsumos().stream()
                .filter(item -> item.getPecasInsumosId().equals(cmd.pecaInsumoId()))
                .findFirst().ifPresentOrElse(item -> {
                    if (item.getQuantidade() < cmd.quantidade()) {
                        Integer diferenca = cmd.quantidade() - item.getQuantidade();
                        pecaInsumo.removerEstoque(diferenca);
                    } else if (item.getQuantidade() > cmd.quantidade()) {
                        Integer diferenca = item.getQuantidade() - cmd.quantidade();
                        pecaInsumo.adicionarEstoque(diferenca);
                    }
                }, () -> pecaInsumo.removerEstoque(cmd.quantidade()));
    }
}