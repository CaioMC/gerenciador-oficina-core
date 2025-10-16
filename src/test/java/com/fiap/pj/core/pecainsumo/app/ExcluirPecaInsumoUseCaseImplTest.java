package com.fiap.pj.core.pecainsumo.app;

import com.fiap.pj.core.pecainsumo.app.gateways.PecaInsumoGateway;
import com.fiap.pj.core.pecainsumo.app.usecase.command.ExcluirPecaInsumoCommand;
import com.fiap.pj.core.pecainsumo.domain.PecaInsumo;
import com.fiap.pj.core.pecainsumo.exception.PecaInsumoExceptions.PecaInsumoComRelacionamentoException;
import com.fiap.pj.core.pecainsumo.util.factory.PecaInsumoTestFactory;
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
class ExcluirPecaInsumoUseCaseImplTest {

    @Mock
    private PecaInsumoGateway pecaInsumoGateway;

    @InjectMocks
    private ExcluirPecaInsumoUseCaseImpl excluirPecaInsumoUseCaseImpl;

    @Test
    void deveExcluirPecaInsumo() {
        var id = UUID.randomUUID();
        var pecaInsumo = PecaInsumoTestFactory.umPecaInsumo();
        var command = new ExcluirPecaInsumoCommand(id);

        when(pecaInsumoGateway.buscarPorId(id)).thenReturn(Optional.of(pecaInsumo));
        excluirPecaInsumoUseCaseImpl.handle(command);

        verify(pecaInsumoGateway).buscarPorId(id);
        verify(pecaInsumoGateway).excluir(pecaInsumo);
    }

    @Test
    void deveRetornarPecaInsumoComRelacionamentoException() {
        var id = UUID.randomUUID();
        var pecaInsumo = PecaInsumoTestFactory.umPecaInsumo();
        var command = new ExcluirPecaInsumoCommand(id);

        when(pecaInsumoGateway.buscarPorId(id)).thenReturn(Optional.of(pecaInsumo));
        Mockito.doThrow(DataIntegrityViolationException.class)
                .when(pecaInsumoGateway).excluir(Mockito.any(PecaInsumo.class));
        Throwable thrown = catchThrowable(() -> excluirPecaInsumoUseCaseImpl.handle(command));
        assertThat(thrown).isInstanceOf(PecaInsumoComRelacionamentoException.class);
    }

} 