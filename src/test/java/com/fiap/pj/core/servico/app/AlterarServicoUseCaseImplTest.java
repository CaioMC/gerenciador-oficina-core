package com.fiap.pj.core.servico.app;


import com.fiap.pj.core.servico.app.gateways.ServicoGateway;
import com.fiap.pj.core.servico.domain.Servico;
import com.fiap.pj.core.servico.exception.ServicoExceptions.ServicoNaoEncontradoException;
import com.fiap.pj.core.servico.exception.ServicoExceptions.StatusServicoNaoPermiteAlterarException;
import com.fiap.pj.core.servico.util.factory.ServicoTestFactory;
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

import static com.fiap.pj.core.servico.util.factory.ServicoTestFactory.DESCRICAO;
import static com.fiap.pj.core.servico.util.factory.ServicoTestFactory.ID;
import static com.fiap.pj.core.servico.util.factory.ServicoTestFactory.OBSERVACAO;
import static com.fiap.pj.core.servico.util.factory.ServicoTestFactory.VALOR_UNITARIO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AlterarServicoUseCaseImplTest {

    @Captor
    ArgumentCaptor<Servico> servicoArgumentCaptor;
    @Mock
    private ServicoGateway servicoGateway;

    @InjectMocks
    private AlterarServicoUseCaseImpl alterarServicoUseCaseImpl;

    @Test
    void deveAlterarServico() {
        when(servicoGateway.buscarPorId(any(UUID.class))).thenReturn(Optional.of(ServicoTestFactory.umServico()));

        alterarServicoUseCaseImpl.handle(ServicoTestFactory.umAlterarServicoCommand(ID));

        verify(servicoGateway).alterar(servicoArgumentCaptor.capture());
        Servico servicoAlterado = servicoArgumentCaptor.getValue();

        assertNotNull(servicoAlterado);
        assertEquals(ID, servicoAlterado.getId());
        assertEquals(ServicoTestFactory.DESCRICAO_ALTERADA, servicoAlterado.getDescricao());
        assertEquals(ServicoTestFactory.VALOR_UNITARIO_ALTERADO, servicoAlterado.getValorUnitario());
        assertEquals(ServicoTestFactory.OBSERVACAO_ALTERADA, servicoAlterado.getObservacao());

    }

    @Test
    void deveRetornarStatusServicoNaoPermiteAlterarException() {
        var servico = new Servico(ID, DESCRICAO, VALOR_UNITARIO, OBSERVACAO, false);

        when(servicoGateway.buscarPorId(any(UUID.class))).thenReturn(Optional.of(servico));

        var thrown = catchThrowable(() -> alterarServicoUseCaseImpl.handle(ServicoTestFactory.umAlterarServicoCommand(ID)));
        assertThat(thrown).isInstanceOf(StatusServicoNaoPermiteAlterarException.class);

    }

    @Test
    void deveRetornarServicoNaoEncontratoException() {
        var servico = ServicoTestFactory.umServico();

        Mockito.doThrow(new ServicoNaoEncontradoException())
                .when(servicoGateway)
                .buscarPorId(servico.getId());

        var thrown = catchThrowable(() -> alterarServicoUseCaseImpl.handle(ServicoTestFactory.umAlterarServicoCommand(ID)));
        assertThat(thrown).isInstanceOf(ServicoNaoEncontradoException.class);

    }


}
