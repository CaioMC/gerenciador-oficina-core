package com.fiap.pj.core.orcamento.app;


import com.fiap.pj.core.cliente.app.gateways.ClienteGateway;
import com.fiap.pj.core.cliente.util.factory.ClienteTestFactory;
import com.fiap.pj.core.orcamento.app.gateways.OrcamentoGateway;
import com.fiap.pj.core.orcamento.app.usecase.command.AlterarOrcamentoCommand;
import com.fiap.pj.core.orcamento.domain.Orcamento;
import com.fiap.pj.core.orcamento.domain.enums.OrcamentoStatus;
import com.fiap.pj.core.orcamento.exception.OrcamentoExceptions.AlterarOrcamentoStatusInvalidoException;
import com.fiap.pj.core.orcamento.util.factory.OrcamentoTestFactory;
import com.fiap.pj.core.pecainsumo.app.gateways.PecaInsumoGateway;
import com.fiap.pj.core.pecainsumo.util.factory.PecaInsumoTestFactory;
import com.fiap.pj.core.servico.app.gateways.ServicoGateway;
import com.fiap.pj.core.servico.util.factory.ServicoTestFactory;
import com.fiap.pj.core.veiculo.exception.VeiculoExceptions.VeiculoNaoPertenceAoClienteException;
import com.fiap.pj.core.veiculo.util.factory.VeiculoTestFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static com.fiap.pj.core.orcamento.util.factory.OrcamentoTestFactory.CLIENTE_ID_ALTERADO;
import static com.fiap.pj.core.orcamento.util.factory.OrcamentoTestFactory.DESCRICAO_ALTERADO;
import static com.fiap.pj.core.orcamento.util.factory.OrcamentoTestFactory.HODOMENTO_ALTERADO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AlterarOrcamentoUseCaseImplTest {

    @Captor
    ArgumentCaptor<Orcamento> orcamentoArgumentCaptor;

    @Mock
    private OrcamentoGateway orcamentoGateway;

    @Mock
    private ClienteGateway clienteGateway;

    @Mock
    private ServicoGateway servicoGateway;

    @Mock
    private PecaInsumoGateway pecaInsumoGateway;

    @InjectMocks
    private AlterarOrcamentoUseCaseImpl alterarOrcamentoUseCaseImpl;

    @Test
    void deveAlterarOrcamento() {
        var orcamento = OrcamentoTestFactory.umOrcamento();
        orcamento.adicionarServico(OrcamentoTestFactory.umOrcamentoItemServico(orcamento.getId()));

        var cliente = ClienteTestFactory.umClienteAlterado();
        cliente.adicionarVeiculo(VeiculoTestFactory.umVeiculoAlterado(cliente.getId()));

        when(orcamentoGateway.buscarPorId(any(UUID.class))).thenReturn(Optional.of(orcamento));
        when(clienteGateway.buscarPorId(any(UUID.class))).thenReturn(Optional.of(cliente));
        when(servicoGateway.buscarPorId(any(UUID.class))).thenReturn(Optional.of(ServicoTestFactory.umServico()));
        when(pecaInsumoGateway.buscarPorId(any(UUID.class))).thenReturn(Optional.of(PecaInsumoTestFactory.umPecaInsumo()));

        alterarOrcamentoUseCaseImpl.handle(OrcamentoTestFactory.umAlterarOrcamentoCommand(orcamento.getId()));

        verify(orcamentoGateway).salvar(orcamentoArgumentCaptor.capture());
        Orcamento orcamentoAlterado = orcamentoArgumentCaptor.getValue();

        assertNotNull(orcamento);
        assertEquals(OrcamentoTestFactory.ID, orcamentoAlterado.getId());
        assertEquals(DESCRICAO_ALTERADO, orcamentoAlterado.getDescricao());
        assertEquals(CLIENTE_ID_ALTERADO, orcamentoAlterado.getClienteId());
        assertEquals(OrcamentoTestFactory.VEICULO_ID_ALTERADO, orcamentoAlterado.getVeiculoId());
        assertEquals(HODOMENTO_ALTERADO, orcamentoAlterado.getHodometro());
        assertEquals(OrcamentoTestFactory.ORCAMENTO_STATUS, orcamentoAlterado.getStatus());
        assertFalse(orcamento.getServicos().isEmpty());

    }

    @Test
    void deveRetonarAlterarOrcamentoStatusInvalidoException() {
        var orcamento = OrcamentoTestFactory.umOrcamento(OrcamentoStatus.APROVADO);
        orcamento.adicionarServico(OrcamentoTestFactory.umOrcamentoItemServico(orcamento.getId()));

        var cliente = ClienteTestFactory.umClienteAlterado();
        cliente.adicionarVeiculo(VeiculoTestFactory.umVeiculoAlterado(cliente.getId()));

        when(orcamentoGateway.buscarPorId(any(UUID.class))).thenReturn(Optional.of(orcamento));
        when(clienteGateway.buscarPorId(any(UUID.class))).thenReturn(Optional.of(cliente));

        var thrown = catchThrowable(() -> alterarOrcamentoUseCaseImpl.handle(OrcamentoTestFactory.umAlterarOrcamentoCommand(orcamento.getId())));
        assertThat(thrown).isInstanceOf(AlterarOrcamentoStatusInvalidoException.class);

    }

    @Test
    void deveRetonarVeiculoNaoPertenceAoClienteException() {
        var orcamento = OrcamentoTestFactory.umOrcamento();
        when(orcamentoGateway.buscarPorId(any(UUID.class))).thenReturn(Optional.ofNullable(orcamento));

        var cliente = ClienteTestFactory.umCliente();
        cliente.adicionarVeiculo(VeiculoTestFactory.umVeiculo(cliente.getId()));
        when(clienteGateway.buscarPorId(any(UUID.class))).thenReturn(Optional.of(cliente));
        var cmd = new AlterarOrcamentoCommand(orcamento.getId(), DESCRICAO_ALTERADO, CLIENTE_ID_ALTERADO, UUID.randomUUID(), HODOMENTO_ALTERADO, Set.of(), Set.of());

        var thrown = catchThrowable(() -> alterarOrcamentoUseCaseImpl.handle(cmd));
        assertThat(thrown).isInstanceOf(VeiculoNaoPertenceAoClienteException.class);
    }

}
