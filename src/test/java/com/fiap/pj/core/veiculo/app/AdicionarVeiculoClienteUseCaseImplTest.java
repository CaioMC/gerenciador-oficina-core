package com.fiap.pj.core.veiculo.app;


import com.fiap.pj.core.cliente.app.gateways.ClienteGateway;
import com.fiap.pj.core.cliente.domain.Cliente;
import com.fiap.pj.core.cliente.util.factory.ClienteTestFactory;
import com.fiap.pj.core.veiculo.app.gateways.VeiculoGateway;
import com.fiap.pj.core.veiculo.exception.VeiculoExceptions.VeiucloPlacaDuplicadaException;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdicionarVeiculoClienteUseCaseImplTest {

    @Captor
    ArgumentCaptor<Cliente> clienteArgumentCaptor;
    @Mock
    private ClienteGateway clienteGateway;

    @Mock
    private VeiculoGateway veiculoGateway;


    @InjectMocks
    private AdicionarVeiculoClienteUseCaseImpl adicionarVeiculoClienteUseCaseImpl;

    @Test
    void deveAdicionarVeiculoAoCliente() {
        var cliente = ClienteTestFactory.umCliente();
        when(clienteGateway.buscarPorId(any(UUID.class))).thenReturn(Optional.of(cliente));
        Mockito.when(veiculoGateway.existsByPlaca(anyString())).thenReturn(false);

        adicionarVeiculoClienteUseCaseImpl.handle(VeiculoTestFactory.umAdicionarVeiculoClienteCommand(cliente.getId()));

        verify(clienteGateway).alterar(clienteArgumentCaptor.capture());
        Cliente clienteUpdated = clienteArgumentCaptor.getValue();

        assertNotNull(clienteUpdated);
        var veiculo = clienteUpdated.getVeiculos().stream().findFirst().orElse(null);
        assertNotNull(veiculo);
        assertEquals(VeiculoTestFactory.PLACA, veiculo.getPlaca());
        assertEquals(VeiculoTestFactory.MARCA, veiculo.getMarca());
        assertEquals(VeiculoTestFactory.MODELO, veiculo.getModelo());
        assertEquals(VeiculoTestFactory.ANO, veiculo.getAno());
    }

    @Test
    void deveRetonarVeiucloPlacaDuplicadaException() {
        var veiculo = VeiculoTestFactory.umVeiculo(ClienteTestFactory.ID);
        Mockito.when(veiculoGateway.existsByPlaca(veiculo.getPlaca())).thenReturn(true);
        var thrown = catchThrowable(() -> adicionarVeiculoClienteUseCaseImpl.handle(VeiculoTestFactory.umAdicionarVeiculoClienteCommand(ClienteTestFactory.ID)));
        assertThat(thrown).isInstanceOf(VeiucloPlacaDuplicadaException.class);
    }

}
