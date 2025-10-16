package com.fiap.pj.infra.cliente.gateways;

import com.fiap.pj.core.cliente.app.gateways.ClienteGateway;
import com.fiap.pj.core.cliente.domain.Cliente;
import com.fiap.pj.infra.cliente.controller.request.ListarClienteRequest;
import com.fiap.pj.infra.cliente.controller.response.ClienteResponse;
import com.fiap.pj.infra.cliente.persistence.ClienteRepositoryJpa;
import com.fiap.pj.infra.cliente.persistence.specification.ClienteSpecification;
import com.fiap.pj.infra.sk.api.Slice;

import java.util.Optional;
import java.util.UUID;

public class ClienteRepositoryGatewayImpl implements ClienteGateway {

    private final ClienteRepositoryJpa repository;

    public ClienteRepositoryGatewayImpl(ClienteRepositoryJpa repository) {
        this.repository = repository;
    }

    @Override
    public Cliente salvar(Cliente cliente) {
        var clienteEntity = ClienteRepositoryMapper.mapToTable(cliente);
        return ClienteRepositoryMapper.mapToDomain(repository.save(clienteEntity));
    }

    @Override
    public void alterar(Cliente cliente) {
        var clienteEntity = ClienteRepositoryMapper.mapToTable(cliente);
        repository.save(clienteEntity);
    }

    @Override
    public void excluir(Cliente cliente) {
        var clienteEntity = ClienteRepositoryMapper.mapToTable(cliente);
        repository.delete(clienteEntity);
    }

    @Override
    public Optional<Cliente> buscarPorId(UUID id) {
        return repository.findById(id)
                .map(ClienteRepositoryMapper::mapToDomain);

    }

    @Override
    public boolean existsByDocumentoIdentificacaoNumero(String documento) {
        return repository.existsByDocumentoIdentificacaoNumero(documento);
    }

    @Override
    public Slice<ClienteResponse> listarUsuarios(ListarClienteRequest request) {
        var specification = new ClienteSpecification(request.getNome(), request.getDocumentoIdentificacao(), request.getPlaca(), request.getAtivo());
        return repository.findProjectedBy(specification.buildSpecification(), request.getPageable(), ClienteResponse.class);
    }

}
