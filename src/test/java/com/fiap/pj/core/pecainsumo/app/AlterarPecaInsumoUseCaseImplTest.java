package com.fiap.pj.core.pecainsumo.app;

import com.fiap.pj.core.pecainsumo.app.gateways.PecaInsumoGateway;
import com.fiap.pj.core.pecainsumo.domain.PecaInsumo;
import com.fiap.pj.core.pecainsumo.exception.PecaInsumoExceptions.PecaInsumoNaoEncontradoException;
import com.fiap.pj.core.pecainsumo.util.factory.PecaInsumoTestFactory;
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

import static com.fiap.pj.core.pecainsumo.util.factory.PecaInsumoTestFactory.DESCRICAO_ALTERADA;
import static com.fiap.pj.core.pecainsumo.util.factory.PecaInsumoTestFactory.MODELO_VEICULO_ALTERADO;
import static com.fiap.pj.core.pecainsumo.util.factory.PecaInsumoTestFactory.QUANTIDADE_MINIMO_ESTOQUE_ALTERADO;
import static com.fiap.pj.core.pecainsumo.util.factory.PecaInsumoTestFactory.VALOR_UNITARIO_ALTERADO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AlterarPecaInsumoUseCaseImplTest {


    @Captor
    ArgumentCaptor<PecaInsumo> pecaInsumoArgumentCaptor;

    @Mock
    private PecaInsumoGateway pecaInsumoGateway;

    @InjectMocks
    private AlterarPecaInsumoUseCaseImpl alterarPecaInsumoUseCaseImpl;

    @Test
    void deveAlterarPecaInsumo() {
        var id = UUID.randomUUID();
        var pecaInsumo = PecaInsumoTestFactory.umPecaInsumo();
        var command = PecaInsumoTestFactory.umAlterarPecaInsumoCommand(id);

        when(pecaInsumoGateway.buscarPorId(any(UUID.class))).thenReturn(Optional.of(pecaInsumo));

        alterarPecaInsumoUseCaseImpl.handle(command);

        verify(pecaInsumoGateway).alterar(pecaInsumoArgumentCaptor.capture());
        PecaInsumo pecaInsumoAlterado = pecaInsumoArgumentCaptor.getValue();


        assertEquals(MODELO_VEICULO_ALTERADO, pecaInsumoAlterado.getModeloVeiculo());
        assertEquals(DESCRICAO_ALTERADA, pecaInsumoAlterado.getDescricao());
        assertEquals(VALOR_UNITARIO_ALTERADO, pecaInsumoAlterado.getValorUnitario());
        assertEquals(QUANTIDADE_MINIMO_ESTOQUE_ALTERADO, pecaInsumoAlterado.getQuantidadeMinimoEstoque());
    }

    @Test
    void deveRetornarPecaInsumoNaoEncontradaException() {
        var id = UUID.randomUUID();
        var command = PecaInsumoTestFactory.umAlterarPecaInsumoCommand(id);

        Mockito.doThrow(new PecaInsumoNaoEncontradoException())
                .when(pecaInsumoGateway).buscarPorId(id);
        Throwable thrown = catchThrowable(() -> alterarPecaInsumoUseCaseImpl.handle(command));
        assertThat(thrown).isInstanceOf(PecaInsumoNaoEncontradoException.class);
    }
} 