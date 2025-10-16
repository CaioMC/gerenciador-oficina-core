package com.fiap.pj.infra.config;


import com.fiap.pj.core.servico.app.AlterarServicoUseCaseImpl;
import com.fiap.pj.core.servico.app.AtivarServicoUseCaseImpl;
import com.fiap.pj.core.servico.app.CriarServicoUseCaseImpl;
import com.fiap.pj.core.servico.app.ExcluirServicoUseCaseImpl;
import com.fiap.pj.core.servico.app.InativarServicoUseCaseImpl;
import com.fiap.pj.core.servico.app.ListarServicoUseCaseImpl;
import com.fiap.pj.core.servico.app.gateways.ServicoGateway;
import com.fiap.pj.infra.servico.gateways.ServicoRepositoryGatewayImpl;
import com.fiap.pj.infra.servico.persistense.ServicoRepositoryJpa;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicoConfig {


    @Bean
    CriarServicoUseCaseImpl criarServicoUseCase(ServicoGateway servicoGateway
    ) {
        return new CriarServicoUseCaseImpl(servicoGateway);
    }

    @Bean
    AlterarServicoUseCaseImpl alterarServicoUseCase(ServicoGateway servicoGateway) {
        return new AlterarServicoUseCaseImpl(servicoGateway);
    }

    @Bean
    AtivarServicoUseCaseImpl ativarServicoUserCase(ServicoGateway servicoGateway) {
        return new AtivarServicoUseCaseImpl(servicoGateway);
    }

    @Bean
    InativarServicoUseCaseImpl inativarServicoUserCase(ServicoGateway servicoGateway) {
        return new InativarServicoUseCaseImpl(servicoGateway);
    }

    @Bean
    ExcluirServicoUseCaseImpl excluirServicoUseCase(ServicoGateway servicoGateway) {
        return new ExcluirServicoUseCaseImpl(servicoGateway);
    }

    @Bean
    ListarServicoUseCaseImpl listarServicoUseCase(ServicoGateway servicoGateway) {
        return new ListarServicoUseCaseImpl(servicoGateway);
    }

    @Bean
    ServicoRepositoryGatewayImpl servicoRepositoryGateway(ServicoRepositoryJpa servicoRepositoryJpa) {
        return new ServicoRepositoryGatewayImpl(servicoRepositoryJpa);
    }

}
