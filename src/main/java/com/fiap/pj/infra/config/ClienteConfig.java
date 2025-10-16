package com.fiap.pj.infra.config;


import com.fiap.pj.core.cliente.app.AlterarClienteUseCaseImpl;
import com.fiap.pj.core.cliente.app.AtivarClienteUseCaseImpl;
import com.fiap.pj.core.cliente.app.CriarClienteUseCaseImpl;
import com.fiap.pj.core.cliente.app.ExcluirClienteUseCaseImpl;
import com.fiap.pj.core.cliente.app.InativarClienteUseCaseImpl;
import com.fiap.pj.core.cliente.app.ListarClienteUseCaseImpl;
import com.fiap.pj.core.cliente.app.gateways.ClienteGateway;
import com.fiap.pj.core.email.app.usecase.EnviarEmailUseCase;
import com.fiap.pj.infra.cliente.gateways.ClienteRepositoryGatewayImpl;
import com.fiap.pj.infra.cliente.persistence.ClienteRepositoryJpa;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClienteConfig {


    @Bean
    CriarClienteUseCaseImpl criarClienteUseCase(ClienteGateway clienteGateway,
                                                EnviarEmailUseCase enviarEmailUseCase
    ) {
        return new CriarClienteUseCaseImpl(clienteGateway, enviarEmailUseCase);
    }

    @Bean
    AtivarClienteUseCaseImpl ativarClienteUseCase(ClienteGateway clienteGateway) {
        return new AtivarClienteUseCaseImpl(clienteGateway);
    }

    @Bean
    InativarClienteUseCaseImpl inativarClienteUseCase(ClienteGateway clienteGateway) {
        return new InativarClienteUseCaseImpl(clienteGateway);
    }

    @Bean
    AlterarClienteUseCaseImpl alterarClienteUseCase(ClienteGateway clienteGateway
    ) {
        return new AlterarClienteUseCaseImpl(clienteGateway);
    }

    @Bean
    ExcluirClienteUseCaseImpl excluirClienteUseCase(ClienteGateway clienteGateway
    ) {
        return new ExcluirClienteUseCaseImpl(clienteGateway);
    }

    @Bean
    ListarClienteUseCaseImpl listarClienteUseCase(ClienteGateway clienteGateway
    ) {
        return new ListarClienteUseCaseImpl(clienteGateway);
    }

    @Bean
    ClienteGateway clienteGateway(ClienteRepositoryJpa repository
    ) {
        return new ClienteRepositoryGatewayImpl(repository);
    }
}
