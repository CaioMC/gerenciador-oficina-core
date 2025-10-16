package com.fiap.pj.core.veiculo.app;

import com.fiap.pj.core.cliente.app.gateways.ClienteGateway;
import com.fiap.pj.core.cliente.exception.ClienteExceptions.ClienteNaoEncontradoException;
import com.fiap.pj.core.veiculo.app.gateways.VeiculoGateway;
import com.fiap.pj.core.veiculo.app.usecase.RemoverVeiculoClienteUseCase;
import com.fiap.pj.core.veiculo.app.usecase.command.RemoverVeiculoClienteCommand;
import com.fiap.pj.core.veiculo.exception.VeiculoExceptions.VeiculoNaoEncontradoException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class RemoverVeiculoClienteUseCaseImpl implements RemoverVeiculoClienteUseCase {

    private final ClienteGateway clienteGateway;
    private final VeiculoGateway veiculoGateway;

    @Override
    public void handle(RemoverVeiculoClienteCommand cmd) {
        var cliente = clienteGateway.buscarPorId(cmd.clienteId()).orElseThrow(ClienteNaoEncontradoException::new);
        var veiculo = veiculoGateway.buscarPorId(cmd.veiculoId()).orElseThrow(VeiculoNaoEncontradoException::new);

        cliente.validarVeiculo(veiculo.getId());
        cliente.getVeiculos().remove(veiculo);

        clienteGateway.alterar(cliente);
        veiculoGateway.excluir(veiculo);
    }
} 