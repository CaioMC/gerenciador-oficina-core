package com.fiap.pj.core.veiculo.adapter.in.api;


import com.fiap.pj.core.cliente.util.factory.ClienteTestFactory;
import com.fiap.pj.core.util.TestUtils;
import com.fiap.pj.core.veiculo.app.usecase.AdicionarVeiculoClienteUseCase;
import com.fiap.pj.core.veiculo.app.usecase.RemoverVeiculoClienteUseCase;
import com.fiap.pj.core.veiculo.app.usecase.command.AdicionarVeiculoClienteCommand;
import com.fiap.pj.core.veiculo.app.usecase.command.RemoverVeiculoClienteCommand;
import com.fiap.pj.core.veiculo.util.factory.VeiculoTestFactory;
import com.fiap.pj.infra.cliente.controller.ClienteVeiculoController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static com.fiap.pj.core.veiculo.util.factory.VeiculoTestFactory.umAdicionarVeiculoClienteCommand;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class VeiculoControllerTest {

    @Mock
    private AdicionarVeiculoClienteUseCase adicionarVeiculoClienteUseCase;

    @Mock
    private RemoverVeiculoClienteUseCase removerVeiculoClienteUseCase;

    @InjectMocks
    private ClienteVeiculoController clienteVeiculoController;

    private MockMvc mock;

    @BeforeEach
    void setup() {
        mock = MockMvcBuilders.standaloneSetup(clienteVeiculoController).build();
    }

    @Test
    void deveAdicionarVeiculoAoCliente() throws Exception {
        Mockito.when(adicionarVeiculoClienteUseCase.handle(Mockito.any(AdicionarVeiculoClienteCommand.class))).thenReturn(VeiculoTestFactory.umVeiculo(ClienteTestFactory.ID));
        mock.perform(post(
                TestUtils.buildURL(ClienteVeiculoController.PATH.replace("{id}", ClienteTestFactory.ID.toString())))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtils.objectToJson(umAdicionarVeiculoClienteCommand(ClienteTestFactory.ID)))).andExpect(status().is2xxSuccessful());
    }

    @Test
    void deveRemoverVeiculoDoCliente() throws Exception {
        var veiculoId = UUID.randomUUID();
        var command = new RemoverVeiculoClienteCommand(ClienteTestFactory.ID, veiculoId);

        Mockito.doNothing().when(removerVeiculoClienteUseCase).handle(Mockito.any(RemoverVeiculoClienteCommand.class));

        mock.perform(delete(
                TestUtils.buildURL(ClienteVeiculoController.PATH.replace("{id}", ClienteTestFactory.ID.toString())))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtils.objectToJson(command))).andExpect(status().isNoContent());
    }

}
