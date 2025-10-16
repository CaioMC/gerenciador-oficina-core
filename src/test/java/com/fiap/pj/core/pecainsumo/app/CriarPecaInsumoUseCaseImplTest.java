package com.fiap.pj.core.pecainsumo.app;

import com.fiap.pj.core.pecainsumo.app.gateways.PecaInsumoGateway;
import com.fiap.pj.core.pecainsumo.domain.PecaInsumo;
import com.fiap.pj.core.pecainsumo.util.factory.PecaInsumoTestFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.fiap.pj.core.pecainsumo.util.factory.PecaInsumoTestFactory.DESCRICAO;
import static com.fiap.pj.core.pecainsumo.util.factory.PecaInsumoTestFactory.MODELO_VEICULO;
import static com.fiap.pj.core.pecainsumo.util.factory.PecaInsumoTestFactory.QUANTIDADE_ESTOQUE;
import static com.fiap.pj.core.pecainsumo.util.factory.PecaInsumoTestFactory.QUANTIDADE_MINIMO_ESTOQUE;
import static com.fiap.pj.core.pecainsumo.util.factory.PecaInsumoTestFactory.VALOR_UNITARIO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CriarPecaInsumoUseCaseImplTest {

    @Mock
    private PecaInsumoGateway pecaInsumoGateway;

    @InjectMocks
    private CriarPecaInsumoUseCaseImpl criarPecaInsumoUseCaseImpl;

    @Test
    void deveCriarPecaInsumo() {
        when(pecaInsumoGateway.salvar(any(PecaInsumo.class))).thenReturn(PecaInsumoTestFactory.umPecaInsumo());

        var pecaInsumo = criarPecaInsumoUseCaseImpl.handle(PecaInsumoTestFactory.umCriarPecaInsumoCommand());

        assertNotNull(pecaInsumo);
        assertEquals(MODELO_VEICULO, pecaInsumo.getModeloVeiculo());
        assertEquals(DESCRICAO, pecaInsumo.getDescricao());
        assertEquals(VALOR_UNITARIO, pecaInsumo.getValorUnitario());
        assertEquals(QUANTIDADE_ESTOQUE, pecaInsumo.getQuantidadeEstoque());
        assertEquals(QUANTIDADE_MINIMO_ESTOQUE, pecaInsumo.getQuantidadeMinimoEstoque());
    }
} 