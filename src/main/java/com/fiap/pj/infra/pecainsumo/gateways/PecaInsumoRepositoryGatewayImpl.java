package com.fiap.pj.infra.pecainsumo.gateways;

import com.fiap.pj.core.pecainsumo.app.gateways.PecaInsumoGateway;
import com.fiap.pj.core.pecainsumo.domain.PecaInsumo;
import com.fiap.pj.infra.pecainsumo.controller.request.ListarPecaInsumoRequest;
import com.fiap.pj.infra.pecainsumo.controller.response.PecaInsumoResponse;
import com.fiap.pj.infra.pecainsumo.persistence.PecaInsumoRepositoryJpa;
import com.fiap.pj.infra.pecainsumo.persistence.specification.PecaInsumoSpecification;
import com.fiap.pj.infra.sk.api.Slice;

import java.util.Optional;
import java.util.UUID;

public class PecaInsumoRepositoryGatewayImpl implements PecaInsumoGateway {

    private final PecaInsumoRepositoryJpa repository;

    public PecaInsumoRepositoryGatewayImpl(PecaInsumoRepositoryJpa repository) {
        this.repository = repository;
    }

    @Override
    public PecaInsumo salvar(PecaInsumo pecaInsumo) {
        var pecaInsumoEntity = PecaInsumoRepositoryMapper.mapToTable(pecaInsumo);
        return PecaInsumoRepositoryMapper.mapToDomain(repository.save(pecaInsumoEntity));
    }

    @Override
    public void alterar(PecaInsumo pecaInsumo) {
        var pecaInsumoEntity = PecaInsumoRepositoryMapper.mapToTable(pecaInsumo);
        repository.save(pecaInsumoEntity);
    }

    @Override
    public void excluir(PecaInsumo pecaInsumo) {
        var pecaInsumoEntity = PecaInsumoRepositoryMapper.mapToTable(pecaInsumo);
        repository.delete(pecaInsumoEntity);
    }

    @Override
    public Optional<PecaInsumo> buscarPorId(UUID id) {
        return repository.findById(id).map(PecaInsumoRepositoryMapper::mapToDomain);
    }

    @Override
    public Slice<PecaInsumoResponse> listar(ListarPecaInsumoRequest request) {
        var specification = new PecaInsumoSpecification(request.getModeloVeiculo(), request.getDescricao(), request.getEstoqueBaixo());
        return repository.findProjectedBy(specification.buildSpecification(), request.getPageable(), PecaInsumoResponse.class);
    }


}
