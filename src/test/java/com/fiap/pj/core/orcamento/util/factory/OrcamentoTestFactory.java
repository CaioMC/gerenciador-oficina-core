package com.fiap.pj.core.orcamento.util.factory;

import com.fiap.pj.core.cliente.util.factory.ClienteTestFactory;
import com.fiap.pj.core.orcamento.app.usecase.command.AlterarOrcamentoCommand;
import com.fiap.pj.core.orcamento.app.usecase.command.AprovarOrcamentoCommand;
import com.fiap.pj.core.orcamento.app.usecase.command.CriarOrcamentoCommand;
import com.fiap.pj.core.orcamento.app.usecase.command.OrcamentoItemPecaInsumoCommand;
import com.fiap.pj.core.orcamento.app.usecase.command.OrcamentoItemServicoCommand;
import com.fiap.pj.core.orcamento.app.usecase.command.ReprovarOrcamentoCommand;
import com.fiap.pj.core.orcamento.domain.Orcamento;
import com.fiap.pj.core.orcamento.domain.OrcamentoItemPecaInsumo;
import com.fiap.pj.core.orcamento.domain.OrcamentoItemServico;
import com.fiap.pj.core.orcamento.domain.enums.OrcamentoStatus;
import com.fiap.pj.core.pecainsumo.util.factory.PecaInsumoTestFactory;
import com.fiap.pj.core.servico.util.factory.ServicoTestFactory;
import com.fiap.pj.core.usuario.util.factrory.UsuarioTestFactory;
import com.fiap.pj.core.veiculo.util.factory.VeiculoTestFactory;

import java.util.Set;
import java.util.UUID;

public class OrcamentoTestFactory {

    public static final UUID ID = UUID.randomUUID();
    public static final UUID ITEM_SERVICO_ID = UUID.randomUUID();
    public static final Integer ITEM_SERVICO_QUANTIDADE = 1;
    public static final UUID ITEM_PECA_INSUMO_ID = UUID.randomUUID();
    public static final Integer ITEM_PECA_INSUMO_QUANTIDADE = 10;
    public static final UUID CLIENTE_ID = ClienteTestFactory.ID;
    public static final UUID VEICULO_ID = VeiculoTestFactory.ID;
    public static final UUID USUARIO_ID = UsuarioTestFactory.ID;
    public static final String DESCRICAO = "Verificar Freios";
    public static final int HODOMENTO = 21052024;
    public static final OrcamentoStatus ORCAMENTO_STATUS = OrcamentoStatus.AGUARDANDO_APROVACAO;

    public static final UUID CLIENTE_ID_ALTERADO = ClienteTestFactory.ID_ALTERADO;
    public static final UUID VEICULO_ID_ALTERADO = VeiculoTestFactory.ID_ALTERADO;
    public static final String DESCRICAO_ALTERADO = "Verificar amortecedor";
    public static final int HODOMENTO_ALTERADO = 9111989;

    public static Orcamento umOrcamento() {
        return umOrcamento(ORCAMENTO_STATUS);
    }

    public static Orcamento umOrcamentoAprovado() {
        return umOrcamento(OrcamentoStatus.APROVADO);
    }

    public static Orcamento umOrcamento(OrcamentoStatus status) {
        return Orcamento.builder().id(ID)
                .status(status)
                .descricao(DESCRICAO)
                .hodometro(HODOMENTO)
                .veiculoId(VEICULO_ID)
                .clienteId(CLIENTE_ID)
                .usuarioId(USUARIO_ID)
                .build();
    }

    public static OrcamentoItemServico umOrcamentoItemServico(UUID orcamentoId) {
        return new OrcamentoItemServico(ITEM_SERVICO_ID, ServicoTestFactory.ID, orcamentoId, ServicoTestFactory.DESCRICAO, ServicoTestFactory.VALOR_UNITARIO, ITEM_SERVICO_QUANTIDADE);
    }

    public static OrcamentoItemPecaInsumo umOrcamentoItemPecaInsumo(UUID orcamentoId) {
        return new OrcamentoItemPecaInsumo(ITEM_PECA_INSUMO_ID, PecaInsumoTestFactory.ID, orcamentoId, PecaInsumoTestFactory.DESCRICAO, PecaInsumoTestFactory.VALOR_UNITARIO, ITEM_PECA_INSUMO_QUANTIDADE);
    }

    public static CriarOrcamentoCommand umCriarOrcamentoCommand() {
        return new CriarOrcamentoCommand(DESCRICAO, CLIENTE_ID, VEICULO_ID, null, HODOMENTO, Set.of(umOrcamentoItemServicoCommand()), Set.of(umOrcamentoItemPecaInsumoCommand()));
    }

    public static AlterarOrcamentoCommand umAlterarOrcamentoCommand(UUID id) {
        return new AlterarOrcamentoCommand(id, DESCRICAO_ALTERADO, CLIENTE_ID_ALTERADO, VEICULO_ID_ALTERADO, HODOMENTO_ALTERADO, Set.of(umOrcamentoItemServicoCommand()), Set.of(umOrcamentoItemPecaInsumoCommand()));
    }

    public static OrcamentoItemServicoCommand umOrcamentoItemServicoCommand() {
        return new OrcamentoItemServicoCommand(ITEM_SERVICO_ID, ITEM_SERVICO_QUANTIDADE);
    }

    public static OrcamentoItemPecaInsumoCommand umOrcamentoItemPecaInsumoCommand() {
        return new OrcamentoItemPecaInsumoCommand(ITEM_PECA_INSUMO_ID, ITEM_PECA_INSUMO_QUANTIDADE);
    }

    public static ReprovarOrcamentoCommand umReprovarOrcamentoCommand() {
        return new ReprovarOrcamentoCommand(ID);
    }

    public static AprovarOrcamentoCommand umAprovarOrcamentoCommand() {
        return new AprovarOrcamentoCommand(ID);
    }
}