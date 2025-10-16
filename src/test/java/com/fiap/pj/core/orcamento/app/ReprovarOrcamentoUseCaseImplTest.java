package com.fiap.pj.core.orcamento.app;


import com.fiap.pj.core.cliente.app.gateways.ClienteGateway;
import com.fiap.pj.core.cliente.util.factory.ClienteTestFactory;
import com.fiap.pj.core.email.app.usecase.EnviarEmailUseCase;
import com.fiap.pj.core.orcamento.app.gateways.OrcamentoGateway;
import com.fiap.pj.core.orcamento.domain.Orcamento;
import com.fiap.pj.core.orcamento.exception.OrcamentoExceptions.OrcamentoNaoEncontradoException;
import com.fiap.pj.core.orcamento.exception.OrcamentoExceptions.ReprovarOrcamentoStatusInvalidoException;
import com.fiap.pj.core.orcamento.util.factory.OrcamentoTestFactory;
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
class ReprovarOrcamentoUseCaseImplTest {

    @Captor
    ArgumentCaptor<Orcamento> orcamentoArgumentCaptor;

    @Mock
    private OrcamentoGateway orcamentoGateway;

    @Mock
    private ClienteGateway clienteGateway;

    @Mock
    private EnviarEmailUseCase enviarEmailUseCase;

    @InjectMocks
    private ReprovarOrcamentoUseCaseImpl reprovarOrcamentoService;

    @Test
    void deveReprovarOrcamento() {
        var orcamento = OrcamentoTestFactory.umOrcamento();
        orcamento.adicionarServico(OrcamentoTestFactory.umOrcamentoItemServico(orcamento.getId()));

        var cliente = ClienteTestFactory.umCliente();

        when(orcamentoGateway.buscarPorId(any(UUID.class))).thenReturn(Optional.of(orcamento));
        when(clienteGateway.buscarPorId(any(UUID.class))).thenReturn(Optional.of(cliente));

        reprovarOrcamentoService.handle(OrcamentoTestFactory.umReprovarOrcamentoCommand());

        verify(orcamentoGateway).salvar(orcamentoArgumentCaptor.capture());
        Orcamento orcamentoAlterado = orcamentoArgumentCaptor.getValue();

        Assertions.assertTrue(orcamentoAlterado.getStatus().isReprovado());
    }

    @Test
    void deveRetornarStatusOrcamentoNaoPermiteReprovarException() {
        var orcamento = OrcamentoTestFactory.umOrcamentoAprovado();
        when(orcamentoGateway.buscarPorId(any(UUID.class))).thenReturn(Optional.ofNullable(orcamento));

        var thrown = catchThrowable(() -> reprovarOrcamentoService.handle(OrcamentoTestFactory.umReprovarOrcamentoCommand()));
        assertThat(thrown).isInstanceOf(ReprovarOrcamentoStatusInvalidoException.class);

    }

    @Test
    void deveRetornarOrcamentoNaoEncontratoException() {
        Mockito.doThrow(new OrcamentoNaoEncontradoException())
                .when(orcamentoGateway)
                .buscarPorId(any(UUID.class));

        var thrown = catchThrowable(() -> reprovarOrcamentoService.handle(OrcamentoTestFactory.umReprovarOrcamentoCommand()));
        assertThat(thrown).isInstanceOf(OrcamentoNaoEncontradoException.class);
    }
}