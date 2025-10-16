package com.fiap.pj.core.veiculo.app;

import com.fiap.pj.core.cliente.app.gateways.ClienteGateway;
import com.fiap.pj.core.cliente.domain.Cliente;
import com.fiap.pj.core.cliente.util.factory.ClienteTestFactory;
import com.fiap.pj.core.veiculo.app.gateways.VeiculoGateway;
import com.fiap.pj.core.veiculo.app.usecase.command.RemoverVeiculoClienteCommand;
import com.fiap.pj.core.veiculo.domain.Veiculo;
import com.fiap.pj.core.veiculo.exception.VeiculoExceptions.VeiculoNaoEncontradoException;
import com.fiap.pj.core.veiculo.exception.VeiculoExceptions.VeiculoNaoPertenceAoClienteException;
import com.fiap.pj.core.veiculo.util.factory.VeiculoTestFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RemoverVeiculoClienteUseCaseImplTest {

    @Captor
    ArgumentCaptor<Cliente> clienteArgumentCaptor;
    @Captor
    ArgumentCaptor<Veiculo> veiculoArgumentCaptor;

    @Mock
    private ClienteGateway clienteGateway;
    @Mock
    private VeiculoGateway veiculoGateway;

    @InjectMocks
    private RemoverVeiculoClienteUseCaseImpl removerVeiculoClienteUseCaseImpl;

    @Test
    void deveRemoverVeiculoDoCliente() {
        var cliente = ClienteTestFactory.umCliente();
        var veiculo = VeiculoTestFactory.umVeiculo(cliente.getId());
        cliente.adicionarVeiculo(veiculo);

        when(clienteGateway.buscarPorId(any(UUID.class))).thenReturn(Optional.of(cliente));
        when(veiculoGateway.buscarPorId(veiculo.getId())).thenReturn(Optional.of(veiculo));

        var command = new RemoverVeiculoClienteCommand(cliente.getId(), veiculo.getId());
        removerVeiculoClienteUseCaseImpl.handle(command);

        verify(clienteGateway).alterar(clienteArgumentCaptor.capture());
        verify(veiculoGateway).excluir(veiculoArgumentCaptor.capture());

        Cliente clienteUpdated = clienteArgumentCaptor.getValue();
        Veiculo veiculoDeleted = veiculoArgumentCaptor.getValue();

        assertNotNull(clienteUpdated);
        assertNotNull(veiculoDeleted);
        assertThat(clienteUpdated.getVeiculos()).isEmpty();
        assertThat(veiculoDeleted.getId()).isEqualTo(veiculo.getId());
    }

    @Test
    void deveRetornarVeiculoNaoPertenceAoClienteException() {
        var cliente = ClienteTestFactory.umCliente();
        var veiculo = VeiculoTestFactory.umVeiculo(UUID.randomUUID()); // Veículo de outro cliente

        when(clienteGateway.buscarPorId(any(UUID.class))).thenReturn(Optional.of(cliente));
        when(veiculoGateway.buscarPorId(veiculo.getId())).thenReturn(Optional.of(veiculo));

        var command = new RemoverVeiculoClienteCommand(cliente.getId(), veiculo.getId());
        var thrown = catchThrowable(() -> removerVeiculoClienteUseCaseImpl.handle(command));

        assertThat(thrown).isInstanceOf(VeiculoNaoPertenceAoClienteException.class);
    }

    @Test
    void deveRetornarVeiculoNaoEncontradoException() {
        var cliente = ClienteTestFactory.umCliente();
        var veiculo = VeiculoTestFactory.umVeiculo(UUID.randomUUID());

        when(clienteGateway.buscarPorId(any(UUID.class))).thenReturn(Optional.of(cliente));
       
        Mockito.doThrow(new VeiculoNaoEncontradoException())
                .when(veiculoGateway)
                .buscarPorId(veiculo.getId());

        var command = new RemoverVeiculoClienteCommand(cliente.getId(), veiculo.getId());
        var thrown = catchThrowable(() -> removerVeiculoClienteUseCaseImpl.handle(command));

        assertThat(thrown).isInstanceOf(VeiculoNaoEncontradoException.class);
    }
} 