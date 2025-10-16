package com.fiap.pj.infra.servico.gateways;

import com.fiap.pj.core.servico.app.gateways.ServicoGateway;
import com.fiap.pj.core.servico.domain.Servico;
import com.fiap.pj.infra.servico.controller.request.ListarServicoRequest;
import com.fiap.pj.infra.servico.controller.response.ServicoResponse;
import com.fiap.pj.infra.servico.persistense.ServicoRepositoryJpa;
import com.fiap.pj.infra.servico.persistense.specification.ServicoSpecification;
import com.fiap.pj.infra.sk.api.Slice;

import java.util.Optional;
import java.util.UUID;

public class ServicoRepositoryGatewayImpl implements ServicoGateway {

    private final ServicoRepositoryJpa repository;

    public ServicoRepositoryGatewayImpl(ServicoRepositoryJpa repository) {
        this.repository = repository;
    }

    @Override
    public Servico salvar(Servico servico) {
        var servicoEntity = ServicoRepositoryMapper.mapToTable(servico);
        return ServicoRepositoryMapper.mapToDomain(repository.save(servicoEntity));
    }

    @Override
    public void alterar(Servico servico) {
        var servicoEntity = ServicoRepositoryMapper.mapToTable(servico);
        repository.save(servicoEntity);
    }

    @Override
    public void excluir(Servico servico) {
        var servicoEntity = ServicoRepositoryMapper.mapToTable(servico);
        repository.delete(servicoEntity);
    }

    @Override
    public Optional<Servico> buscarPorId(UUID id) {
        return repository.findById(id).map(ServicoRepositoryMapper::mapToDomain);
    }

    @Override
    public Slice<ServicoResponse> listarServico(ListarServicoRequest request) {
        var specification = new ServicoSpecification(request.getNome(), request.getAtivo());
        return repository.findProjectedBy(specification.buildSpecification(), request.getPageable(), ServicoResponse.class);
    }


}
