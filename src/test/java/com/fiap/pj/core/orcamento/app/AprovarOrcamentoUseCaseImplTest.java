package com.fiap.pj.core.orcamento.app;


import com.fiap.pj.core.cliente.app.gateways.ClienteGateway;
import com.fiap.pj.core.cliente.util.factory.ClienteTestFactory;
import com.fiap.pj.core.email.app.usecase.EnviarEmailUseCase;
import com.fiap.pj.core.orcamento.app.gateways.OrcamentoGateway;
import com.fiap.pj.core.orcamento.domain.Orcamento;
import com.fiap.pj.core.orcamento.domain.enums.OrcamentoStatus;
import com.fiap.pj.core.orcamento.exception.OrcamentoExceptions.AlterarOrcamentoStatusInvalidoException;
import com.fiap.pj.core.orcamento.exception.OrcamentoExceptions.OrcamentoNaoEncontradoException;
import com.fiap.pj.core.orcamento.util.factory.OrcamentoTestFactory;
import com.fiap.pj.core.ordemservico.app.usecase.CriarOrdemServicoUseCase;
import com.fiap.pj.core.ordemservico.app.usecase.command.CriarOrdemServicoCommand;
import org.junit.jupiter.api.Assertions;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AprovarOrcamentoUseCaseImplTest {

    @Captor
    ArgumentCaptor<Orcamento> orcamentoArgumentCaptor;

    @Mock
    private OrcamentoGateway orcamentoGateway;

    @Mock
    private CriarOrdemServicoUseCase criarOrdemServicoUseCase;

    @Mock
    private ClienteGateway clienteGateway;

    @Mock
    private EnviarEmailUseCase enviarEmailUseCase;

    @InjectMocks
    private AprovarOrcamentoUseCaseImpl aprovarOrcamentoUseCaseImpl;

    @Test
    void deveAprovarOrcamento() {
        var orcamento = OrcamentoTestFactory.umOrcamento();
        orcamento.adicionarServico(OrcamentoTestFactory.umOrcamentoItemServico(orcamento.getId()));

        var cliente = ClienteTestFactory.umCliente();

        when(orcamentoGateway.buscarPorId(any(UUID.class))).thenReturn(Optional.of(orcamento));
        when(criarOrdemServicoUseCase.handle(any(CriarOrdemServicoCommand.class))).thenReturn(UUID.randomUUID());
        when(clienteGateway.buscarPorId(any(UUID.class))).thenReturn(Optional.of(cliente));

        aprovarOrcamentoUseCaseImpl.handle(OrcamentoTestFactory.umAprovarOrcamentoCommand());

        verify(orcamentoGateway).salvar(orcamentoArgumentCaptor.capture());
        Orcamento orcamentoAlterado = orcamentoArgumentCaptor.getValue();

        Assertions.assertTrue(orcamentoAlterado.getStatus().isAprovado());
    }

    @Test
    void deveRetornarStatusOrcamentoNaoPermiteAprovarException() {
        var orcamento = OrcamentoTestFactory.umOrcamento(OrcamentoStatus.REPROVADO);
        when(orcamentoGateway.buscarPorId(any(UUID.class))).thenReturn(Optional.ofNullable(orcamento));

        var thrown = catchThrowable(() -> aprovarOrcamentoUseCaseImpl.handle(OrcamentoTestFactory.umAprovarOrcamentoCommand()));
        assertThat(thrown).isInstanceOf(AlterarOrcamentoStatusInvalidoException.class);

    }

    @Test
    void deveRetornarOrcamentoNaoEncontratoException() {
        Mockito.doThrow(new OrcamentoNaoEncontradoException())
                .when(orcamentoGateway)
                .buscarPorId(any(UUID.class));

        var thrown = catchThrowable(() -> aprovarOrcamentoUseCaseImpl.handle(OrcamentoTestFactory.umAprovarOrcamentoCommand()));
        assertThat(thrown).isInstanceOf(OrcamentoNaoEncontradoException.class);

    }

}
