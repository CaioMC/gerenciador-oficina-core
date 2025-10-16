package com.fiap.pj.infra.orcamento.gateways;

import com.fiap.pj.core.orcamento.app.gateways.OrcamentoGateway;
import com.fiap.pj.core.orcamento.domain.Orcamento;
import com.fiap.pj.infra.orcamento.controller.request.ListarOrcamentoRequest;
import com.fiap.pj.infra.orcamento.controller.response.OrcamentoResponse;
import com.fiap.pj.infra.orcamento.persistence.OrcamentoRepositoryJpa;
import com.fiap.pj.infra.orcamento.persistence.specification.OrcamentoSpecification;
import com.fiap.pj.infra.sk.api.Slice;

import java.util.Optional;
import java.util.UUID;

public class OrcamentoRepositoryGatewayImpl implements OrcamentoGateway {


    private final OrcamentoRepositoryJpa repository;

    public OrcamentoRepositoryGatewayImpl(OrcamentoRepositoryJpa repository) {
        this.repository = repository;
    }

    @Override
    public Orcamento salvar(Orcamento orcamento) {
        var orcamentoEntity = repository.save(OrcamentoRepositoryMapper.mapToTable(orcamento));
        return OrcamentoRepositoryMapper.mapToDomain(orcamentoEntity);
    }

    @Override
    public void alterar(Orcamento orcamento) {
        repository.save(OrcamentoRepositoryMapper.mapToTable(orcamento));
    }

    @Override
    public void excluir(Orcamento orcamento) {
        repository.delete(OrcamentoRepositoryMapper.mapToTable(orcamento));
    }

    @Override
    public Optional<Orcamento> buscarPorId(UUID id) {
        return repository.findById(id).map(OrcamentoRepositoryMapper::mapToDomain);
    }

    @Override
    public Slice<OrcamentoResponse> listar(ListarOrcamentoRequest request) {
        var specification = new OrcamentoSpecification(request.getClienteId());
        return repository.findProjectedBy(specification.buildSpecification(), request.getPageable(), OrcamentoResponse.class);
    }
}
