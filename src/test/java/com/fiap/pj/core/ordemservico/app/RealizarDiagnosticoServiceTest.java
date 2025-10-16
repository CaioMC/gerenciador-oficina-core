package com.fiap.pj.core.ordemservico.app;


import com.fiap.pj.core.ordemservico.app.gateways.OrdemServicoGateway;
import com.fiap.pj.core.ordemservico.domain.OrdemServico;
import com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus;
import com.fiap.pj.core.ordemservico.exception.OrdemServicoExceptions.OrdemServicoStatusInvalidoDiagnosticoException;
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

import static com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus.AGUARDANDO_APROVACAO;
import static com.fiap.pj.core.ordemservico.domain.enums.OrdemServicoStatus.EM_DIAGNOSTICO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RealizarDiagnosticoServiceTest {

    @Captor
    ArgumentCaptor<OrdemServico> ordemServicoArgumentCaptor;

    @Mock
    private OrdemServicoGateway ordemServicoGateway;

    @InjectMocks
    private RealizarDiagnosticoUseCaseImpl realizarDiagnosticoUseCaseImpl;

    @Test
    void deveAlteraStatusOsEmDiagnostico() {
        TestSecurityConfig.setAuthentication();
        var ordemServico = OrdemServicoTestFactory.umaOrdemServico(EM_DIAGNOSTICO);

        when(ordemServicoGateway.buscarPorId(ordemServico.getId())).thenReturn(Optional.of(ordemServico));

        realizarDiagnosticoUseCaseImpl.handle(OrdemServicoTestFactory.umRealizarDiagnosticoOrdemServicoCommand());

        verify(ordemServicoGateway).salvar(ordemServicoArgumentCaptor.capture());
        OrdemServico ordemServicoAlterada = ordemServicoArgumentCaptor.getValue();

        Assertions.assertNotNull(ordemServico);
        Assertions.assertEquals(OrdemServicoStatus.EM_DIAGNOSTICO, ordemServicoAlterada.getStatus());
        Assertions.assertEquals(OrdemServicoTestFactory.DESCRICAO, ordemServicoAlterada.getDiagnostico().getDescricao());

    }

    @Test
    void deveRetornarOrdemServicoStatusInvalidoDiagnosticoException() {
        var ordemServico = OrdemServicoTestFactory.umaOrdemServico(AGUARDANDO_APROVACAO);
        when(ordemServicoGateway.buscarPorId(ordemServico.getId())).thenReturn(Optional.of(ordemServico));

        var thrown = catchThrowable(() -> realizarDiagnosticoUseCaseImpl.handle(OrdemServicoTestFactory.umRealizarDiagnosticoOrdemServicoCommand()));
        assertThat(thrown).isInstanceOf(OrdemServicoStatusInvalidoDiagnosticoException.class);
    }

}
