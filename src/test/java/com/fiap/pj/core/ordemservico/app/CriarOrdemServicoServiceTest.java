package com.fiap.pj.core.ordemservico.app;


import com.fiap.pj.core.ordemservico.app.gateways.OrdemServicoGateway;
import com.fiap.pj.core.ordemservico.domain.OrdemServico;
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

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CriarOrdemServicoServiceTest {

    @Captor
    ArgumentCaptor<OrdemServico> ordemServicoArgumentCaptor;

    @Mock
    private OrdemServicoGateway ordemServicoGateway;

    @InjectMocks
    private CriarOrdemServicoUseCaseImpl criarOrdemServicoUseCaseImpl;

    @Test
    void deveCriarOrdemServico() {
        TestSecurityConfig.setAuthentication();

        var cmd = OrdemServicoTestFactory.umCriarOrdemServicoCommand();

        criarOrdemServicoUseCaseImpl.handle(cmd);

        verify(ordemServicoGateway).salvar(ordemServicoArgumentCaptor.capture());
        OrdemServico ordemServico = ordemServicoArgumentCaptor.getValue();

        Assertions.assertNotNull(ordemServico);
        Assertions.assertEquals(ordemServico.getClienteId(), cmd.clienteId());
        Assertions.assertEquals(ordemServico.getVeiculoId(), cmd.veiculoId());

    }

}
