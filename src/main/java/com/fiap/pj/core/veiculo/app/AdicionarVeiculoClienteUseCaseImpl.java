package com.fiap.pj.core.veiculo.app;


import com.fiap.pj.core.cliente.app.gateways.ClienteGateway;
import com.fiap.pj.core.cliente.exception.ClienteExceptions.ClienteNaoEncontradoException;
import com.fiap.pj.core.veiculo.app.gateways.VeiculoGateway;
import com.fiap.pj.core.veiculo.app.usecase.AdicionarVeiculoClienteUseCase;
import com.fiap.pj.core.veiculo.app.usecase.command.AdicionarVeiculoClienteCommand;
import com.fiap.pj.core.veiculo.domain.Veiculo;
import com.fiap.pj.core.veiculo.exception.VeiculoExceptions.VeiucloPlacaDuplicadaException;

import java.util.UUID;

public class AdicionarVeiculoClienteUseCaseImpl implements AdicionarVeiculoClienteUseCase {

    private final ClienteGateway clienteGateway;
    private final VeiculoGateway veiculoGateway;

    public AdicionarVeiculoClienteUseCaseImpl(ClienteGateway clienteGateway, VeiculoGateway veiculoGateway) {
        this.clienteGateway = clienteGateway;
        this.veiculoGateway = veiculoGateway;
    }

    @Override
    public Veiculo handle(AdicionarVeiculoClienteCommand cmd) {

        if (veiculoGateway.existsByPlaca(cmd.placa())) {
            throw new VeiucloPlacaDuplicadaException();
        }

        var cliente = clienteGateway.buscarPorId(cmd.clienteId()).orElseThrow(ClienteNaoEncontradoException::new);

        var veiculo = Veiculo.builder().id(UUID.randomUUID())
                .placa(cmd.placa())
                .marca(cmd.marca())
                .modelo(cmd.modelo())
                .ano(cmd.ano())
                .clienteId(cliente.getId())
                .build();

        cliente.adicionarVeiculo(veiculo);

        clienteGateway.alterar(cliente);

        return veiculo;

    }
}
