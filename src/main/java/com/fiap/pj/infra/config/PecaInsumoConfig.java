package com.fiap.pj.infra.config;


import com.fiap.pj.core.pecainsumo.app.AlterarPecaInsumoUseCaseImpl;
import com.fiap.pj.core.pecainsumo.app.CriarPecaInsumoUseCaseImpl;
import com.fiap.pj.core.pecainsumo.app.ExcluirPecaInsumoUseCaseImpl;
import com.fiap.pj.core.pecainsumo.app.ListarPecaInsumoUseCaseImpl;
import com.fiap.pj.core.pecainsumo.app.gateways.PecaInsumoGateway;
import com.fiap.pj.infra.pecainsumo.gateways.PecaInsumoRepositoryGatewayImpl;
import com.fiap.pj.infra.pecainsumo.persistence.PecaInsumoRepositoryJpa;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PecaInsumoConfig {

    @Bean
    CriarPecaInsumoUseCaseImpl criarPecaInsumoUseCase(PecaInsumoGateway pecaInsumoGateway
    ) {
        return new CriarPecaInsumoUseCaseImpl(pecaInsumoGateway);
    }

    @Bean
    AlterarPecaInsumoUseCaseImpl alterarPecaInsumoUseCase(PecaInsumoGateway pecaInsumoGateway) {
        return new AlterarPecaInsumoUseCaseImpl(pecaInsumoGateway);
    }

    @Bean
    ExcluirPecaInsumoUseCaseImpl excluirPecaInsumoUseCase(PecaInsumoGateway pecaInsumoGateway) {
        return new ExcluirPecaInsumoUseCaseImpl(pecaInsumoGateway);
    }

    @Bean
    ListarPecaInsumoUseCaseImpl listarPecaInsumoUseCase(PecaInsumoGateway pecaInsumoGateway) {
        return new ListarPecaInsumoUseCaseImpl(pecaInsumoGateway);
    }

    @Bean
    PecaInsumoGateway pecaInsumoGateway(PecaInsumoRepositoryJpa pecaInsumoRepositoryJpa) {
        return new PecaInsumoRepositoryGatewayImpl(pecaInsumoRepositoryJpa);
    }

}
