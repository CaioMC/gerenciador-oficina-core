package com.fiap.pj.core.servico.app;


import com.fiap.pj.core.servico.app.gateways.ServicoGateway;
import com.fiap.pj.core.servico.app.usecase.command.AtivarServicoCommand;
import com.fiap.pj.core.servico.domain.Servico;
import com.fiap.pj.core.servico.exception.ServicoExceptions.ServicoNaoEncontradoException;
import com.fiap.pj.core.servico.util.factory.ServicoTestFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AtivarServicoUseCaseImplTest {

    @Mock
    private ServicoGateway servicoGateway;

    @InjectMocks
    private AtivarServicoUseCaseImpl ativarServicoUseCaseImpl;

    @Test
    void deveAtivarServico() {
        var id = ServicoTestFactory.ID;
        when(servicoGateway.buscarPorId(id)).thenReturn(Optional.of(ServicoTestFactory.umServico()));
        ativarServicoUseCaseImpl.handle(new AtivarServicoCommand(id));
        verify(servicoGateway).alterar(Mockito.any(Servico.class));
    }

    @Test
    void deveRetonarServicoNaoEncontradoException() {
        var id = UUID.randomUUID();

        Mockito.doThrow(new ServicoNaoEncontradoException())
                .when(servicoGateway)
                .buscarPorId(id);

        var thrown = catchThrowable(() -> ativarServicoUseCaseImpl.handle(new AtivarServicoCommand(id)));
        assertThat(thrown).isInstanceOf(ServicoNaoEncontradoException.class);

    }

}
