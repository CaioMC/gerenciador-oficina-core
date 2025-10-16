package com.fiap.pj.core.servico.app;


import com.fiap.pj.core.servico.app.gateways.ServicoGateway;
import com.fiap.pj.core.servico.domain.Servico;
import com.fiap.pj.core.servico.util.factory.ServicoTestFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CriarServicoUseCaseImplTest {

    @Mock
    private ServicoGateway servicoGateway;

    @InjectMocks
    private CriarServicoUseCaseImpl criarServicoUseCaseImpl;

    @Test
    void deveCriarServico() {
        when(servicoGateway.salvar(any(Servico.class))).thenReturn(ServicoTestFactory.umServico());

        var service = criarServicoUseCaseImpl.handle(ServicoTestFactory.umCriarServicoCommand());

        assertNotNull(service);
        assertEquals(ServicoTestFactory.ID, service.getId());
        assertEquals(ServicoTestFactory.DESCRICAO, service.getDescricao());
        assertEquals(ServicoTestFactory.VALOR_UNITARIO, service.getValorUnitario());
        assertEquals(ServicoTestFactory.OBSERVACAO, service.getObservacao());

    }
}
