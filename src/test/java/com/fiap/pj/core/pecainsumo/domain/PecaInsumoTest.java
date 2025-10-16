package com.fiap.pj.core.pecainsumo.domain;

import com.fiap.pj.core.pecainsumo.exception.PecaInsumoExceptions.PecasInsumoQuantidadeEstoqueInsuficienteException;
import com.fiap.pj.core.pecainsumo.exception.PecaInsumoExceptions.PecasInsumoQuantidadeMenorIgualAZeroException;
import com.fiap.pj.core.pecainsumo.util.factory.PecaInsumoTestFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.fiap.pj.core.pecainsumo.util.factory.PecaInsumoTestFactory.DESCRICAO;
import static com.fiap.pj.core.pecainsumo.util.factory.PecaInsumoTestFactory.DESCRICAO_ALTERADA;
import static com.fiap.pj.core.pecainsumo.util.factory.PecaInsumoTestFactory.MODELO_VEICULO;
import static com.fiap.pj.core.pecainsumo.util.factory.PecaInsumoTestFactory.MODELO_VEICULO_ALTERADO;
import static com.fiap.pj.core.pecainsumo.util.factory.PecaInsumoTestFactory.QUANTIDADE_ESTOQUE;
import static com.fiap.pj.core.pecainsumo.util.factory.PecaInsumoTestFactory.QUANTIDADE_MINIMO_ESTOQUE;
import static com.fiap.pj.core.pecainsumo.util.factory.PecaInsumoTestFactory.QUANTIDADE_MINIMO_ESTOQUE_ALTERADO;
import static com.fiap.pj.core.pecainsumo.util.factory.PecaInsumoTestFactory.VALOR_UNITARIO;
import static com.fiap.pj.core.pecainsumo.util.factory.PecaInsumoTestFactory.VALOR_UNITARIO_ALTERADO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PecaInsumoTest {

    @Test
    @DisplayName("Deve criar com sucesso uma instância de peça/insumo.")
    void deveCriarPecaInsumo() {
        var pecaInsumo = PecaInsumoTestFactory.umPecaInsumo();

        assertEquals(MODELO_VEICULO, pecaInsumo.getModeloVeiculo());
        assertEquals(DESCRICAO, pecaInsumo.getDescricao());
        assertEquals(VALOR_UNITARIO, pecaInsumo.getValorUnitario());
        assertEquals(QUANTIDADE_ESTOQUE, pecaInsumo.getQuantidadeEstoque());
        assertEquals(QUANTIDADE_MINIMO_ESTOQUE, pecaInsumo.getQuantidadeMinimoEstoque());
    }

    @Test
    @DisplayName("Deve adicionar estoque com sucesso.")
    void deveAdicionarEstoque() {
        var pecaInsumo = PecaInsumoTestFactory.umPecaInsumo();
        var quantidadeInicial = pecaInsumo.getQuantidadeEstoque();

        pecaInsumo.adicionarEstoque(5);

        assertEquals(quantidadeInicial + 5, pecaInsumo.getQuantidadeEstoque());
    }

    @Test
    @DisplayName("Deve remover estoque com sucesso.")
    void deveRemoverEstoque() {
        var pecaInsumo = PecaInsumoTestFactory.umPecaInsumo();
        var quantidadeInicial = pecaInsumo.getQuantidadeEstoque();

        pecaInsumo.removerEstoque(3);

        assertEquals(quantidadeInicial - 3, pecaInsumo.getQuantidadeEstoque());
    }

    @Test
    @DisplayName("Deve atualizar dados com sucesso.")
    void deveAtualizarDados() {
        var pecaInsumo = PecaInsumoTestFactory.umPecaInsumo();

        pecaInsumo.atualizarDados(DESCRICAO_ALTERADA, MODELO_VEICULO_ALTERADO, VALOR_UNITARIO_ALTERADO, QUANTIDADE_MINIMO_ESTOQUE_ALTERADO);


        assertEquals(MODELO_VEICULO_ALTERADO, pecaInsumo.getModeloVeiculo());
        assertEquals(DESCRICAO_ALTERADA, pecaInsumo.getDescricao());
        assertEquals(VALOR_UNITARIO_ALTERADO, pecaInsumo.getValorUnitario());
        assertEquals(QUANTIDADE_MINIMO_ESTOQUE_ALTERADO, pecaInsumo.getQuantidadeMinimoEstoque());
    }

    @Test
    @DisplayName("Deve identificar estoque baixo quando quantidade é menor ou igual ao mínimo.")
    void deveIdentificarEstoqueBaixo() {
        var pecaInsumo = PecaInsumoTestFactory.umPecaInsumo();

        // Estoque inicial (10) é maior que o mínimo (5), então não está baixo
        assertFalse(pecaInsumo.estoqueBaixo());

        // Estoque igual ao mínimo (5)
        pecaInsumo.removerEstoque(5);
        assertTrue(pecaInsumo.estoqueBaixo());

        // Estoque abaixo do mínimo (4)
        pecaInsumo.removerEstoque(1);
        assertTrue(pecaInsumo.estoqueBaixo());

        // Estoque acima do mínimo (9)
        pecaInsumo.adicionarEstoque(5);
        assertFalse(pecaInsumo.estoqueBaixo());
    }

    @Nested
    class FalhaNaCriacao {

        @Test
        void deveFalharComNomeInvalido() {
            assertThrows(NullPointerException.class,
                    () -> new PecaInsumo(PecaInsumoTestFactory.ID,
                            null,
                            DESCRICAO,
                            VALOR_UNITARIO, QUANTIDADE_ESTOQUE, QUANTIDADE_MINIMO_ESTOQUE));
        }

        @Test
        void deveFalharComDescricaoInvalida() {
            assertThrows(NullPointerException.class,
                    () -> new PecaInsumo(PecaInsumoTestFactory.ID,
                            MODELO_VEICULO,
                            null,
                            VALOR_UNITARIO, QUANTIDADE_ESTOQUE, QUANTIDADE_MINIMO_ESTOQUE));
        }

        @Test
        void deveFalharComValorUnitarioInvalido() {
            assertThrows(NullPointerException.class,
                    () -> new PecaInsumo(PecaInsumoTestFactory.ID,
                            MODELO_VEICULO,
                            DESCRICAO,
                            null, QUANTIDADE_ESTOQUE, QUANTIDADE_MINIMO_ESTOQUE));
        }

        @Test
        void deveFalharComQuantidadeEstoqueInvalida() {
            assertThrows(NullPointerException.class,
                    () -> new PecaInsumo(PecaInsumoTestFactory.ID,
                            MODELO_VEICULO,
                            DESCRICAO,
                            VALOR_UNITARIO, null, QUANTIDADE_MINIMO_ESTOQUE));
        }

        @Test
        void deveFalharComQuantidadeMinimoEstoqueInvalida() {
            assertThrows(NullPointerException.class,
                    () -> new PecaInsumo(PecaInsumoTestFactory.ID,
                            MODELO_VEICULO,
                            DESCRICAO,
                            VALOR_UNITARIO, QUANTIDADE_ESTOQUE, null));
        }
    }

    @Nested
    class FalhaNaGestaoDeEstoque {

        @Test
        void deveFalharAoAdicionarQuantidadeNegativa() {
            var pecaInsumo = PecaInsumoTestFactory.umPecaInsumo();

            assertThrows(PecasInsumoQuantidadeMenorIgualAZeroException.class,
                    () -> pecaInsumo.adicionarEstoque(-1));
        }

        @Test
        void deveFalharAoAdicionarQuantidadeZero() {
            var pecaInsumo = PecaInsumoTestFactory.umPecaInsumo();

            assertThrows(PecasInsumoQuantidadeMenorIgualAZeroException.class,
                    () -> pecaInsumo.adicionarEstoque(0));
        }

        @Test
        void deveFalharAoRemoverQuantidadeNegativa() {
            var pecaInsumo = PecaInsumoTestFactory.umPecaInsumo();

            assertThrows(PecasInsumoQuantidadeMenorIgualAZeroException.class,
                    () -> pecaInsumo.removerEstoque(-1));
        }

        @Test
        void deveFalharAoRemoverQuantidadeZero() {
            var pecaInsumo = PecaInsumoTestFactory.umPecaInsumo();

            assertThrows(PecasInsumoQuantidadeMenorIgualAZeroException.class,
                    () -> pecaInsumo.removerEstoque(0));
        }

        @Test
        void deveFalharAoRemoverQuantidadeMaiorQueEstoque() {
            var pecaInsumo = PecaInsumoTestFactory.umPecaInsumo();

            assertThrows(PecasInsumoQuantidadeEstoqueInsuficienteException.class,
                    () -> pecaInsumo.removerEstoque(QUANTIDADE_ESTOQUE + 1));
        }
    }
} 