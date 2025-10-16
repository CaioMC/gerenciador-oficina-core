package com.fiap.pj.core.ordemservico.app;


import com.fiap.pj.core.cliente.app.gateways.ClienteGateway;
import com.fiap.pj.core.cliente.util.factory.ClienteTestFactory;
import com.fiap.pj.core.email.app.usecase.EnviarEmailUseCase;
import com.fiap.pj.core.ordemservico.app.gateways.OrdemServicoGateway;
import com.fiap.pj.core.ordemservico.domain.OrdemServico;
import com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus;
import com.fiap.pj.core.ordemservico.exception.OrdemServicoExceptions.OrdemServicoStatusInvalidoAguardandoAprovacaoException;
import com.fiap.pj.core.ordemservico.util.factory.OrdemServicoTestFactory;
import com.fiap.pj.util.TestSecurityConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus.CRIADA;
import static com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus.EM_DIAGNOSTICO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MoverAguardandoAprovacaoServiceTest {

    @Captor
    ArgumentCaptor<OrdemServico> ordemServicoArgumentCaptor;

    @Mock
    private OrdemServicoGateway ordemServicoGateway;

    @Mock
    private ClienteGateway clienteGateway;

    @Mock
    private EnviarEmailUseCase enviarEmailUseCase;

    @InjectMocks
    private MoverAguardandoAprovacaoUseCaseImpl moverAguardandoAprovacaoUseCaseImpl;

    @Test
    void deveAlteraStatusOsAguardandoAprovacao() {
        TestSecurityConfig.setAuthentication();

        var ordemServico = OrdemServicoTestFactory.umaOrdemServico(EM_DIAGNOSTICO);
        var cliente = ClienteTestFactory.umCliente();

        when(ordemServicoGateway.buscarPorId(ordemServico.getId())).thenReturn(Optional.of(ordemServico));
        when(clienteGateway.buscarPorId(any(UUID.class))).thenReturn(Optional.of(cliente));

        moverAguardandoAprovacaoUseCaseImpl.handle(ordemServico.getId());

        verify(ordemServicoGateway).salvar(ordemServicoArgumentCaptor.capture());
        OrdemServico ordemServicoAlterada = ordemServicoArgumentCaptor.getValue();

        Assertions.assertNotNull(ordemServico);
        Assertions.assertEquals(OrdemServicoStatus.AGUARDANDO_APROVACAO, ordemServicoAlterada.getStatus());
    }

    @Test
    void deveRetornarOrdemServicoStatusInvalidoAguardandoAprovacaoException() {
        var ordemServico = OrdemServicoTestFactory.umaOrdemServico(CRIADA);
        when(ordemServicoGateway.buscarPorId(ordemServico.getId())).thenReturn(Optional.of(ordemServico));

        var thrown = catchThrowable(() -> moverAguardandoAprovacaoUseCaseImpl.handle(ordemServico.getId()));
        assertThat(thrown).isInstanceOf(OrdemServicoStatusInvalidoAguardandoAprovacaoException.class);
    }
}