package com.fiap.pj.infra.veiculo.gateways;

import com.fiap.pj.core.veiculo.app.gateways.VeiculoGateway;
import com.fiap.pj.core.veiculo.domain.Veiculo;
import com.fiap.pj.infra.veiculo.persistence.VeiculoEntity;
import com.fiap.pj.infra.veiculo.persistence.VeiculoRepositoryJpa;

import java.util.Optional;
import java.util.UUID;

public class VeiculoRepositoryGatewayImpl implements VeiculoGateway {

    private final VeiculoRepositoryJpa repository;

    public VeiculoRepositoryGatewayImpl(VeiculoRepositoryJpa repository) {
        this.repository = repository;
    }

    @Override
    public boolean existsByPlaca(String placa) {
        return repository.existsByPlaca(placa);
    }

    @Override
    public Optional<Veiculo> buscarPorId(UUID id) {
        return repository.findById(id).map(VeiculoRepositoryMapper::mapToDomain);
    }

    @Override
    public Veiculo salvar(Veiculo veiculo) {
        VeiculoEntity veiculoEntity = repository.save(VeiculoRepositoryMapper.mapToTable(veiculo));
        return VeiculoRepositoryMapper.mapToDomain(veiculoEntity);
    }

    @Override
    public void alterar(Veiculo veiculo) {
        repository.save(VeiculoRepositoryMapper.mapToTable(veiculo));
    }


    @Override
    public void excluir(Veiculo veiculo) {
        repository.delete(VeiculoRepositoryMapper.mapToTable(veiculo));
    }
}
