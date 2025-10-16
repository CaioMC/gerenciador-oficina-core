package com.fiap.pj.core.orcamento.app;

import com.fiap.pj.core.cliente.app.gateways.ClienteGateway;
import com.fiap.pj.core.cliente.domain.Cliente;
import com.fiap.pj.core.cliente.exception.ClienteExceptions.ClienteNaoEncontradoException;
import com.fiap.pj.core.email.app.usecase.EnviarEmailUseCase;
import com.fiap.pj.core.email.app.usecase.command.EnviarEmailCommand;
import com.fiap.pj.core.email.domain.enums.Template;
import com.fiap.pj.core.orcamento.app.gateways.OrcamentoGateway;
import com.fiap.pj.core.orcamento.app.usecase.CriarOrcamentoUseCase;
import com.fiap.pj.core.orcamento.app.usecase.command.CriarOrcamentoCommand;
import com.fiap.pj.core.orcamento.domain.Orcamento;
import com.fiap.pj.core.orcamento.domain.enums.OrcamentoStatus;
import com.fiap.pj.core.pecainsumo.app.gateways.PecaInsumoGateway;
import com.fiap.pj.core.servico.app.gateways.ServicoGateway;
import com.fiap.pj.core.usuario.app.gateways.UsuarioGateway;
import com.fiap.pj.core.usuario.domain.Usuario;
import com.fiap.pj.core.usuario.exception.UsuarioExceptions.UsuarioNaoEncontradoException;
import com.fiap.pj.core.veiculo.app.gateways.VeiculoGateway;
import com.fiap.pj.core.veiculo.domain.Veiculo;
import com.fiap.pj.core.veiculo.exception.VeiculoExceptions.VeiculoNaoEncontradoException;
import com.fiap.pj.infra.util.security.SecurityContextUtils;

import java.util.List;
import java.util.UUID;


public class CriarOrcamentoUseCaseImpl extends OrcamentoUseCaseImpl implements CriarOrcamentoUseCase {

    private final OrcamentoGateway orcamentoGateway;
    private final ClienteGateway clienteGateway;
    private final VeiculoGateway veiculoGateway;
    private final UsuarioGateway usuarioGateway;

    private final EnviarEmailUseCase enviarEmailUseCase;

    public CriarOrcamentoUseCaseImpl(
            ServicoGateway servicoGateway,
            PecaInsumoGateway pecaInsumoGateway,
            OrcamentoGateway orcamentoGateway,
            ClienteGateway clienteGateway, VeiculoGateway veiculoGateway, UsuarioGateway usuarioGateway, EnviarEmailUseCase enviarEmailUseCase
    ) {
        super(servicoGateway, pecaInsumoGateway);

        this.orcamentoGateway = orcamentoGateway;
        this.clienteGateway = clienteGateway;
        this.veiculoGateway = veiculoGateway;
        this.usuarioGateway = usuarioGateway;

        this.enviarEmailUseCase = enviarEmailUseCase;
    }

    @Override
    public Orcamento handle(CriarOrcamentoCommand cmd) {

        Cliente cliente = clienteGateway.buscarPorId(cmd.getClienteId()).orElseThrow(ClienteNaoEncontradoException::new);
        cliente.validarVeiculo(cmd.getVeiculoId());

        UUID usuarioId = SecurityContextUtils.getUsuarioId();
        Usuario usuario = this.usuarioGateway.buscarPorId(usuarioId).orElseThrow(UsuarioNaoEncontradoException::new);

        Orcamento orcamento = Orcamento.builder()
                .id(UUID.randomUUID())
                .descricao(cmd.getDescricao())
                .clienteId(cmd.getClienteId())
                .veiculoId(cmd.getVeiculoId())
                .usuarioId(usuarioId)
                .ordemServicoId(cmd.getOrdemServicoId())
                .status(OrcamentoStatus.AGUARDANDO_APROVACAO)
                .hodometro(cmd.getHodometro())
                .build();

        super.buildItemServico(orcamento, cmd.getServicos());
        super.buildItemPecaInsumo(orcamento, cmd.getPecasInsumos());

        this.orcamentoGateway.salvar(orcamento);

        Veiculo veiculo = this.veiculoGateway.buscarPorId(cmd.getVeiculoId()).orElseThrow(VeiculoNaoEncontradoException::new);

        this.enviarEmailUseCase.handle(
                new EnviarEmailCommand(
                        cliente.getEmail(),
                        Template.ORCAMENTO_CRIADO,
                        List.of(
                                orcamento.getId(),
                                veiculo.getPlaca(),
                                usuario.getNome()
                        )
                )
        );

        return orcamento;
    }
}
