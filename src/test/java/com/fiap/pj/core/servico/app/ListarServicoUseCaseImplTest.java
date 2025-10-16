package com.fiap.pj.core.servico.app;

import com.fiap.pj.core.servico.app.gateways.ServicoGateway;
import com.fiap.pj.core.servico.util.factory.ServicoTestFactory;
import com.fiap.pj.infra.servico.controller.request.ListarServicoRequest;
import com.fiap.pj.infra.servico.controller.response.ServicoResponse;
import com.fiap.pj.infra.sk.api.Slice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static com.fiap.pj.core.servico.util.factory.ServicoTestFactory.DESCRICAO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListarServicoUseCaseImplTest {

    @Mock
    private ServicoGateway servicoGateway;

    @InjectMocks
    private ListarServicoUseCaseImpl listarServicoUseCaseImpl;

    @Test
    void deveListarServicos() {
        var request = new ListarServicoRequest(DESCRICAO, PageRequest.of(0, 10), true);
        ServicoResponse servicoResponseMock = mock(ServicoResponse.class);
        when(servicoResponseMock.getId()).thenReturn(ServicoTestFactory.ID.toString());
        when(servicoResponseMock.getDescricao()).thenReturn(DESCRICAO);
        when(servicoResponseMock.getValorUnitario()).thenReturn(ServicoTestFactory.VALOR_UNITARIO);
        when(servicoResponseMock.getObservacao()).thenReturn(ServicoTestFactory.OBSERVACAO);

        var slice = new Slice<>(false, List.of(servicoResponseMock));
        when(servicoGateway.listarServico(request))
                .thenReturn(slice);

        var result = listarServicoUseCaseImpl.handle(request);

        assertNotNull(result);
        assertEquals(1, result.getItems().size());

        var item = result.getItems().stream().findFirst().orElse(null);

        assertEquals(ServicoTestFactory.ID.toString(), item.getId());
        assertEquals(DESCRICAO, item.getDescricao());
        assertEquals(ServicoTestFactory.VALOR_UNITARIO, item.getValorUnitario());
        assertEquals(ServicoTestFactory.OBSERVACAO, item.getObservacao());
    }

} 