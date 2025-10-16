package com.fiap.pj.infra.ordemservico.gateways;

import com.fiap.pj.core.ordemservico.app.gateways.OrdemServicoGateway;
import com.fiap.pj.core.ordemservico.domain.OrdemServico;
import com.fiap.pj.infra.ordemservico.controller.request.ListarOrdemServicoRequest;
import com.fiap.pj.infra.ordemservico.controller.response.AcompanhamentoOrdemServicoResponse;
import com.fiap.pj.infra.ordemservico.controller.response.OrdemServicoResponse;
import com.fiap.pj.infra.ordemservico.persistence.OrdemServicoRepositoryJpa;
import com.fiap.pj.infra.ordemservico.persistence.specification.OrdemServicoSpecification;
import com.fiap.pj.infra.sk.api.Slice;

import java.util.Optional;
import java.util.UUID;

public class OrdemServicoRepositoryGatewayImpl implements OrdemServicoGateway {


    private final OrdemServicoRepositoryJpa repository;

    public OrdemServicoRepositoryGatewayImpl(OrdemServicoRepositoryJpa repository) {
        this.repository = repository;
    }

    @Override
    public OrdemServico salvar(OrdemServico ordemServico) {
        var ordemServicoEntity = repository.save(OrdemServicoRepositoryMapper.mapToTable(ordemServico));
        return OrdemServicoRepositoryMapper.mapToDomain(ordemServicoEntity);
    }

    @Override
    public void alterar(OrdemServico ordemServico) {
        repository.save(OrdemServicoRepositoryMapper.mapToTable(ordemServico));
    }

    @Override
    public void excluir(OrdemServico ordemServico) {
        repository.delete(OrdemServicoRepositoryMapper.mapToTable(ordemServico));
    }

    @Override
    public Optional<OrdemServico> buscarPorId(UUID id) {
        return repository.findById(id).map(OrdemServicoRepositoryMapper::mapToDomain);
    }

    @Override
    public Slice<OrdemServicoResponse> listar(ListarOrdemServicoRequest request) {
        var specification = OrdemServicoSpecification.builder()
                .id(request.getId())
                .clienteId(request.getClienteId())
                .veiculoId(request.getVeiculoId())
                .usuarioId(request.getUsuarioId())
                .status(request.getStatus())
                .dataCriacaoDe(request.getDataCriacaoDe())
                .dataCriacaoAte(request.getDataCriacaoAte())
                .dataConclusaoAte(request.getDataConclusaoAte())
                .dataConclusaoDe(request.getDataConclusaoDe())
                .build();

        return repository.findProjectedBy(specification.buildSpecification(), request.getPageable(), OrdemServicoResponse.class);
    }

    @Override
    public Optional<AcompanhamentoOrdemServicoResponse> buscarAcompanhamentoByOrdemServicoId(UUID ordemServicoId) {
        return repository.findById(ordemServicoId, AcompanhamentoOrdemServicoResponse.class);
    }
}
