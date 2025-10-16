package com.fiap.pj.core.orcamento.domain;

import com.fiap.pj.core.orcamento.util.factory.OrcamentoTestFactory;
import com.fiap.pj.core.pecainsumo.exception.PecaInsumoExceptions.PecasInsumoQuantidadeMenorIgualAZeroException;
import com.fiap.pj.core.pecainsumo.util.factory.PecaInsumoTestFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.fiap.pj.core.orcamento.util.factory.OrcamentoTestFactory.ITEM_PECA_INSUMO_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrcamentoTest {


    @Test
    @DisplayName("Deve criar com sucesso uma instância de orcamento.")
    void deveCriarServico() {
        var orcamento = OrcamentoTestFactory.umOrcamento();
        orcamento.adicionarServico(OrcamentoTestFactory.umOrcamentoItemServico(orcamento.getId()));
        orcamento.adicionaPecaInsumo(OrcamentoTestFactory.umOrcamentoItemPecaInsumo(orcamento.getId()));

        assertEquals(OrcamentoTestFactory.ID, orcamento.getId());
        assertEquals(OrcamentoTestFactory.DESCRICAO, orcamento.getDescricao());
        assertEquals(OrcamentoTestFactory.CLIENTE_ID, orcamento.getClienteId());
        assertEquals(OrcamentoTestFactory.VEICULO_ID, orcamento.getVeiculoId());
        assertEquals(OrcamentoTestFactory.HODOMENTO, orcamento.getHodometro());
        assertEquals(OrcamentoTestFactory.ORCAMENTO_STATUS, orcamento.getStatus());
        assertFalse(orcamento.getServicos().isEmpty());
    }

    @Nested
    class FalhaNaCriacao {

        @Test
        void deveFalharComIdInvalido() {
            assertThrows(NullPointerException.class,
                    () -> new Orcamento(null,
                            null,
                            null,
                            null, null, 0, null, null));
        }

        @Test
        void deveFalharComClienteIdInvalido() {
            assertThrows(NullPointerException.class,
                    () -> new Orcamento(OrcamentoTestFactory.ID,
                            null,
                            null,
                            null, null, 0, null, null));
        }

        @Test
        void deveFalharComVeiculoIdInvalido() {
            assertThrows(NullPointerException.class,
                    () -> new Orcamento(OrcamentoTestFactory.ID,
                            null,
                            OrcamentoTestFactory.CLIENTE_ID,
                            null, null, 0, null, null));
        }

        @Test
        void deveFalharComStatusIdInvalido() {
            assertThrows(NullPointerException.class,
                    () -> new Orcamento(OrcamentoTestFactory.ID,
                            null,
                            OrcamentoTestFactory.CLIENTE_ID,
                            OrcamentoTestFactory.VEICULO_ID, null, 0, null, null));
        }

        @Test
        void deveFalharComQuatidadeDePecaInsumoInvalida() {

            var orcamento = OrcamentoTestFactory.umOrcamento();
            var pecasInsumo = new OrcamentoItemPecaInsumo(ITEM_PECA_INSUMO_ID, PecaInsumoTestFactory.ID, orcamento.getId(), PecaInsumoTestFactory.DESCRICAO, PecaInsumoTestFactory.VALOR_UNITARIO, 0);

            assertThrows(PecasInsumoQuantidadeMenorIgualAZeroException.class, () ->
                    orcamento.adicionaPecaInsumo(pecasInsumo));
        }
    }
}

