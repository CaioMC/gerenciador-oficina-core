package com.fiap.pj.core.orcamento.app;


import com.fiap.pj.core.cliente.app.gateways.ClienteGateway;
import com.fiap.pj.core.cliente.util.factory.ClienteTestFactory;
import com.fiap.pj.core.email.app.usecase.EnviarEmailUseCase;
import com.fiap.pj.core.orcamento.app.gateways.OrcamentoGateway;
import com.fiap.pj.core.orcamento.app.usecase.command.CriarOrcamentoCommand;
import com.fiap.pj.core.orcamento.domain.Orcamento;
import com.fiap.pj.core.orcamento.util.factory.OrcamentoTestFactory;
import com.fiap.pj.core.pecainsumo.app.gateways.PecaInsumoGateway;
import com.fiap.pj.core.pecainsumo.util.factory.PecaInsumoTestFactory;
import com.fiap.pj.core.servico.app.gateways.ServicoGateway;
import com.fiap.pj.core.servico.util.factory.ServicoTestFactory;
import com.fiap.pj.core.usuario.app.gateways.UsuarioGateway;
import com.fiap.pj.core.usuario.util.factrory.UsuarioTestFactory;
import com.fiap.pj.core.veiculo.app.gateways.VeiculoGateway;
import com.fiap.pj.core.veiculo.exception.VeiculoExceptions.VeiculoNaoPertenceAoClienteException;
import com.fiap.pj.core.veiculo.util.factory.VeiculoTestFactory;
import com.fiap.pj.util.TestSecurityConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static com.fiap.pj.core.orcamento.util.factory.OrcamentoTestFactory.CLIENTE_ID;
import static com.fiap.pj.core.orcamento.util.factory.OrcamentoTestFactory.DESCRICAO;
import static com.fiap.pj.core.orcamento.util.factory.OrcamentoTestFactory.HODOMENTO;
import static com.fiap.pj.core.orcamento.util.factory.OrcamentoTestFactory.umOrcamentoItemPecaInsumoCommand;
import static com.fiap.pj.core.orcamento.util.factory.OrcamentoTestFactory.umOrcamentoItemServicoCommand;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CriarOrcamentoUseCaseImplTest {

    @Mock
    private OrcamentoGateway orcamentoGateway;

    @Mock
    private ClienteGateway clienteGateway;

    @Mock
    private ServicoGateway servicoGateway;

    @Mock
    private PecaInsumoGateway pecaInsumoGateway;

    @Mock
    private UsuarioGateway usuarioGateway;

    @Mock
    private VeiculoGateway veiculoGateway;

    @Mock
    private EnviarEmailUseCase enviarEmailUseCase;

    @InjectMocks
    private CriarOrcamentoUseCaseImpl criarOrcamentoUseCaseImpl;

    @Test
    void deveCriarOrcamento() {
        TestSecurityConfig.setAuthentication();

        var orcamento = OrcamentoTestFactory.umOrcamento();
        orcamento.adicionarServico(OrcamentoTestFactory.umOrcamentoItemServico(orcamento.getId()));
        orcamento.adicionaPecaInsumo(OrcamentoTestFactory.umOrcamentoItemPecaInsumo(orcamento.getId()));

        when(orcamentoGateway.salvar(any(Orcamento.class))).thenReturn(orcamento);

        var cliente = ClienteTestFactory.umCliente();
        cliente.adicionarVeiculo(VeiculoTestFactory.umVeiculo(cliente.getId()));

        var usuario = UsuarioTestFactory.umUsuario();
        var veiculo = VeiculoTestFactory.umVeiculo(cliente.getId());

        when(clienteGateway.buscarPorId(any(UUID.class))).thenReturn(Optional.of(cliente));
        when(servicoGateway.buscarPorId(any(UUID.class))).thenReturn(Optional.of(ServicoTestFactory.umServico()));
        when(pecaInsumoGateway.buscarPorId(any(UUID.class))).thenReturn(Optional.of(PecaInsumoTestFactory.umPecaInsumo()));
        when(usuarioGateway.buscarPorId(any(UUID.class))).thenReturn(Optional.of(usuario));
        when(veiculoGateway.buscarPorId(any(UUID.class))).thenReturn(Optional.of(veiculo));

        var cmd = OrcamentoTestFactory.umCriarOrcamentoCommand();

        var orcamentoCriado = criarOrcamentoUseCaseImpl.handle(cmd);

        assertNotNull(orcamento);
        assertEquals(DESCRICAO, orcamentoCriado.getDescricao());
        assertEquals(cmd.getClienteId(), orcamentoCriado.getClienteId());
        assertEquals(cmd.getVeiculoId(), orcamentoCriado.getVeiculoId());
        assertEquals(cmd.getHodometro(), orcamentoCriado.getHodometro());
        assertEquals(OrcamentoTestFactory.ORCAMENTO_STATUS, orcamentoCriado.getStatus());
        assertFalse(orcamento.getServicos().isEmpty());
    }

    @Test
    void deveRetonarVeiculoNaoPertenceAoClienteException() {

        var cliente = ClienteTestFactory.umCliente();
        cliente.adicionarVeiculo(VeiculoTestFactory.umVeiculo(cliente.getId()));

        when(clienteGateway.buscarPorId(any(UUID.class))).thenReturn(Optional.of(cliente));

        var cmd = new CriarOrcamentoCommand(DESCRICAO, CLIENTE_ID, UUID.randomUUID(), null, HODOMENTO, Set.of(umOrcamentoItemServicoCommand()), Set.of(umOrcamentoItemPecaInsumoCommand()));

        var thrown = catchThrowable(() -> criarOrcamentoUseCaseImpl.handle(cmd));
        assertThat(thrown).isInstanceOf(VeiculoNaoPertenceAoClienteException.class);

    }
}
