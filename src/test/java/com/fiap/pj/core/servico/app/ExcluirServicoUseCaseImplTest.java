package com.fiap.pj.core.servico.app;


import com.fiap.pj.core.servico.app.gateways.ServicoGateway;
import com.fiap.pj.core.servico.app.usecase.command.ExcluirServicoCommand;
import com.fiap.pj.core.servico.domain.Servico;
import com.fiap.pj.core.servico.exception.ServicoExceptions.ServicoComRelacionamentoException;
import com.fiap.pj.core.servico.util.factory.ServicoTestFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExcluirServicoUseCaseImplTest {

    @Mock
    private ServicoGateway servicoGateway;

    @InjectMocks
    private ExcluirServicoUseCaseImpl excluirServicoUseCaseImpl;


    @Test
    void deveExcluirServico() {
        var id = UUID.randomUUID();
        when(servicoGateway.buscarPorId(id)).thenReturn(Optional.of(ServicoTestFactory.umServico()));
        excluirServicoUseCaseImpl.handle(new ExcluirServicoCommand(id));
        verify(servicoGateway).excluir(Mockito.any(Servico.class));
    }

    @Test
    void deveRetornarServicoComRelacionamentoException() {
        var id = UUID.randomUUID();

        when(servicoGateway.buscarPorId(id)).thenReturn(Optional.of(ServicoTestFactory.umServico()));
        Mockito.doThrow(DataIntegrityViolationException.class)
                .when(servicoGateway).excluir(Mockito.any(Servico.class));
        Throwable thrown = catchThrowable(() -> excluirServicoUseCaseImpl.handle(new ExcluirServicoCommand(id)));
        assertThat(thrown).isInstanceOf(ServicoComRelacionamentoException.class);
    }

}
