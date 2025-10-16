package com.fiap.pj.infra.config;


import com.fiap.pj.core.cliente.app.gateways.ClienteGateway;
import com.fiap.pj.core.email.app.usecase.EnviarEmailUseCase;
import com.fiap.pj.core.ordemservico.app.*;
import com.fiap.pj.core.ordemservico.app.gateways.OrdemServicoGateway;
import com.fiap.pj.core.ordemservico.app.usecase.*;
import com.fiap.pj.infra.ordemservico.gateways.OrdemServicoRepositoryGatewayImpl;
import com.fiap.pj.infra.ordemservico.persistence.OrdemServicoRepositoryJpa;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrdemServicoConfig {

    @Bean
    CriarOrdemServicoUseCase criarOrdemServicoUseCase(OrdemServicoGateway ordemServicoGateway
    ) {
        return new CriarOrdemServicoUseCaseImpl(ordemServicoGateway);
    }

    @Bean
    AlterarStatusOsAguardandoAprovacaoUseCase alterarStatusOsAguardandoAprovacaoUseCase(OrdemServicoGateway ordemServicoGateway, EnviarEmailUseCase enviarEmailUseCase, ClienteGateway clienteGateway) {
        return new MoverAguardandoAprovacaoUseCaseImpl(ordemServicoGateway, enviarEmailUseCase, clienteGateway);
    }


    @Bean
    MoverAguardandoRetiradaUseCase moverAguardandoRetiradaUseCase(OrdemServicoGateway ordemServicoGateway, EnviarEmailUseCase enviarEmailUseCase, ClienteGateway clienteGateway) {
        return new MoverAguardandoRetiradaUseCaseImpl(ordemServicoGateway, enviarEmailUseCase, clienteGateway);
    }

    @Bean
    MoverEmDiagnosticoUseCase moverEmDiagnosticoUseCase(OrdemServicoGateway ordemServicoGateway, EnviarEmailUseCase enviarEmailUseCase, ClienteGateway clienteGateway) {
        return new MoverEmDiagnosticoUseCaseImpl(ordemServicoGateway, enviarEmailUseCase, clienteGateway);
    }

    @Bean
    MoverEmExecucaoUseCase moverEmExecucaoUseCase(OrdemServicoGateway ordemServicoGateway, EnviarEmailUseCase enviarEmailUseCase, ClienteGateway clienteGateway) {
        return new MoverEmExecucaoUseCaseImpl(ordemServicoGateway, enviarEmailUseCase, clienteGateway);
    }

    @Bean
    MoverEntregueUseCase moverEntregueUseCase(OrdemServicoGateway ordemServicoGateway, EnviarEmailUseCase enviarEmailUseCase, ClienteGateway clienteGateway) {
        return new MoverEntregueUseCaseImpl(ordemServicoGateway, enviarEmailUseCase, clienteGateway);
    }

    @Bean
    MoverFinalizadaUseCase moverFinalizadaUseCase(OrdemServicoGateway ordemServicoGateway, EnviarEmailUseCase enviarEmailUseCase, ClienteGateway clienteGateway) {
        return new MoverFinalizadaUseCaseImpl(ordemServicoGateway, enviarEmailUseCase, clienteGateway);
    }

    @Bean
    RealizarDiagnosticoUseCase realizarDiagnosticoUseCase(OrdemServicoGateway ordemServicoGateway) {
        return new RealizarDiagnosticoUseCaseImpl(ordemServicoGateway);
    }

    @Bean
    ListarOrdemServicoUseCase listarOrdemServicoUseCase(OrdemServicoGateway ordemServicoGateway) {
        return new ListarOrdemServicoUseCaseImpl(ordemServicoGateway);
    }

    @Bean
    BuscarAcompanhamentoByOrdemServicoIdUseCase buscarAcompanhamentoByOrdemServicoIdUseCase(OrdemServicoGateway ordemServicoGateway) {
        return new BuscarAcompanhamentoByOrdemServicoIdUseCaseImpl(ordemServicoGateway);
    }

    @Bean
    OrdemServicoGateway ordemServicoGateway(OrdemServicoRepositoryJpa ordemServicoRepositoryJpa) {
        return new OrdemServicoRepositoryGatewayImpl(ordemServicoRepositoryJpa);
    }

}
